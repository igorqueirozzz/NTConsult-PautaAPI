package br.com.ntconsult.NTConsult.services.implementation;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntconsult.NTConsult.DTO.PautaDTO;
import br.com.ntconsult.NTConsult.domain.Abstract.CPFValidacao;
import br.com.ntconsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntconsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntconsult.NTConsult.domain.model.Pauta;
import br.com.ntconsult.NTConsult.domain.model.Sessao;
import br.com.ntconsult.NTConsult.domain.model.VotacoesRealizadas;
import br.com.ntconsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntconsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntconsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import br.com.ntconsult.NTConsult.exception.VotoException;
import br.com.ntconsult.NTConsult.services.PautaService;
import br.com.ntconsult.NTConsult.services.SessaoService;
import br.com.ntconsult.NTConsult.exception.PautaException;
import br.com.ntconsult.NTConsult.exception.SessaoException;
import br.com.ntconsult.NTConsult.services.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    PautaValidacao pautaValidacao;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoService sessaoService;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;

    @Autowired
    VotacaoService votacaoService;

    @Autowired
    CPFValidacao cpfValidacao;

    @Override
    public List<Pauta> findAllFinalizadas() {
        return pautaRepository.findAllFinalizadas();
    }

    @Override
    public List<Pauta> findAllNaoIniciadas() {
        return pautaRepository.findAllNaoIniciadas();
    }

    @Override
    public List<Pauta> findAllEmVotacao() {
        return pautaRepository.findAllEmVotacao();
    }

    @Override
    public ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception {
        Sessao sessao = new Sessao();
        sessao.setStatus_sessao(StatusSessaoEnum.NAOINICIADA);
        sessaoRepository.saveAndFlush(sessao);

        Pauta pauta = new Pauta();
        pauta.setAssunto(pautaDTO.getAssunto());
        pauta.setDescricao(pautaDTO.getDescricao());
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pauta.setSessao(sessao);
        pautaValidacao.validar(pauta);
        pautaRepository.saveAndFlush(pauta);
        sessao.setPauta_id(pauta.getId_pauta());
        sessaoRepository.save(sessao);
//        sessaoService.aplicarSessao(pauta.getId_pauta());
        return ResponseEntity.ok("Pauta Nº" + pauta.getId_pauta() + " cadastrada com sucesso!");
    }

    @Override
    public void alterarStatus(Long pauta_id, StatusPautaEnum statusPautaEnum) {
        Optional<Pauta> pauta = pautaRepository.findById(pauta_id);
        if (pauta.isPresent()){
            Pauta pautaAlterar = pauta.get();
            pautaAlterar.setStatus(statusPautaEnum);
            pautaRepository.save(pautaAlterar);
        }
    }

    @Override
    public void votarEmUmaPauta(CooperadoDTO cooperadoDTO) throws Exception {

        cpfValidacao.validarCPF(cooperadoDTO.getCpf());
        votacaoService.validarVoto(cooperadoDTO);
        votacaoService.checarSeJaVotou(cooperadoDTO);

        Optional<Pauta> pauta = pautaRepository.findById(cooperadoDTO.getPauta_id());
        Optional<Sessao> sessao = sessaoRepository.findByPautaId(cooperadoDTO.getPauta_id());

        Pauta pautaVotar = new Pauta();
        Sessao sessaoVotar = new Sessao();

        int votoNao = 0;
        int votoSim = 0;

        if (pauta.isPresent() && sessao.isPresent()){
            pautaVotar = pauta.get();
            sessaoVotar = sessao.get();
            checarStatus(pautaVotar, sessaoVotar);
        } else {
            throw new VotoException("ESSA PAUTA NÃO EXISTE");
        }

        if (pautaVotar.getVotos_sim() != null){
            votoSim = pautaVotar.getVotos_sim();
        }

        if (pautaVotar.getVotos_nao() != null){
            votoNao = pautaVotar.getVotos_nao();
        }

        if (cooperadoDTO.getVoto().trim().equalsIgnoreCase("sim")){
            pautaVotar.setVotos_sim(votoSim + 1);
        }else if(cooperadoDTO.getVoto().trim().equalsIgnoreCase("nao") ||
                cooperadoDTO.getVoto().trim().equalsIgnoreCase("não")){
            pautaVotar.setVotos_nao(votoNao + 1);
        }

        pautaRepository.save(pautaVotar);
        cadastrarVotosRealizados(cooperadoDTO);
    }

    @Override
    public void checarStatus(Pauta pauta, Sessao sessao){
        if(pauta.getStatus().equals(StatusPautaEnum.FINALIZADA)){
            throw new PautaException("ESTA PAUTA JÁ FOI VOTADA!");
        }else if(pauta.getStatus().equals(StatusPautaEnum.NAOREALIZADA)){
            throw new PautaException("A SESSÃO DE VOTAÇÃO DESTA PAUTA NÃO FOI INICIADA!");
        }else if(sessao.getStatus_sessao().equals(StatusSessaoEnum.FINALIZADA)){
            throw new SessaoException("ESTA SESSÃO DE VOTOS JÁ FINALIZOU!");
        }else if (sessao.getStatus_sessao().equals(StatusSessaoEnum.NAOINICIADA)){
            throw new SessaoException("ESSA SESSÃO DE VOTOS AINDA NÃO INICIOU!");
        }
    }

    @Override
    public void cadastrarVotosRealizados(CooperadoDTO cooperadoDTO) {
        VotacoesRealizadas votacoesRealizadas = new VotacoesRealizadas();
        votacoesRealizadas.setId_pauta(cooperadoDTO.getPauta_id());
        votacoesRealizadas.setCpf_cooperado(cooperadoDTO.getCpf());
        votacoesRealizadasRepository.save(votacoesRealizadas);
    }

}
