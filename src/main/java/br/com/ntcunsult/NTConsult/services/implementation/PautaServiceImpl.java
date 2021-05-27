package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntcunsult.NTConsult.domain.Abstract.VotacaoValidacao;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.model.VotacoesRealizadas;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntcunsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import br.com.ntcunsult.NTConsult.exception.PautaException;
import br.com.ntcunsult.NTConsult.exception.SessaoException;
import br.com.ntcunsult.NTConsult.services.PautaService;
import br.com.ntcunsult.NTConsult.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    VotacaoValidacao votacaoValidacao;

    @Override
    public ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception {
        Pauta pauta = new Pauta();
        pauta.setAssunto(pautaDTO.getAssunto());
        pauta.setDescricao(pautaDTO.getDescricao());
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaValidacao.validar(pauta);
        pautaRepository.save(pauta);
        sessaoService.aplicarSessao(pauta.getId_pauta());
        return ResponseEntity.ok("Pauta Nº" + pauta.getId_pauta() + " cadastrada com sucesso!");
    }

    @Override
    public void alterarStatus(Long pauta_id, StatusPautaEnum statusPautaEnum) {
        Optional<Pauta> pauta = pautaRepository.findById(pauta_id);
        if (!pauta.isEmpty()){
            Pauta pautaAlterar = pauta.get();
            pautaAlterar.setStatus(statusPautaEnum);
            pautaRepository.save(pautaAlterar);
        }
    }

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
    public void votarEmUmaPauta(CooperadoDTO cooperadoDTO) {
//        cpfValidacao.validarCPF(cooperadoDTO.getCpf());
        votacaoValidacao.validarVoto(cooperadoDTO);
        votacaoValidacao.checarSeJaVotou(cooperadoDTO);

        Optional<Pauta> pauta = pautaRepository.findById(cooperadoDTO.getPauta_id());
        Optional<Sessao> sessao = sessaoRepository.findById(cooperadoDTO.getPauta_id());
        Pauta pautaVotar = new Pauta();
        Sessao sessaoVotar = new Sessao();
        int votoNao = 0;
        int votoSim = 0;

        if (!pauta.isEmpty() && !sessao.isEmpty()){
            pautaVotar = pauta.get();
            sessaoVotar = sessao.get();
            checarStatus(pautaVotar, sessaoVotar);
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
