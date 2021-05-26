package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.Abstract.CPFValidacao;
import br.com.ntcunsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntcunsult.NTConsult.domain.enumeration.ResultadoEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.exception.VotoException;
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

//    @Autowired
//    CPFValidacao cpfValidacao;

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
    public List<Pauta> findAllNaoFinalizadas() {
        return pautaRepository.findAllNaoFinalizadas();
    }

    @Override
    public List<Pauta> findAllEmVotacao() {
        return pautaRepository.findAllEmVotacao();
    }

//    @Override
//    public void votarEmUmaPauta(CooperadoDTO cooperadoDTO) {
//        cpfValidacao.validarCPF(cooperadoDTO.getCpf());
//        VotoException.validarVoto(cooperadoDTO.getVoto());
//    }


}
