package br.com.ntcunsult.NTConsult.services;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PautaService {

    ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception;

    void alterarStatus(Long pauta_id, StatusPautaEnum statusPautaEnum);

    List<Pauta> findAllFinalizadas();

    List<Pauta> findAllNaoFinalizadas();

    List<Pauta> findAllEmVotacao();

//    void votarEmUmaPauta(CooperadoDTO cooperadoDTO);

}
