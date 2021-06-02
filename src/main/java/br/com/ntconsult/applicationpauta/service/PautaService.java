package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.dto.CooperadoDTO;
import br.com.ntconsult.applicationpauta.dto.PautaDTO;
import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.model.Sessao;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PautaService {

    ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception;

    void alterarStatus(Long pauta_id, StatusPautaEnum statusPautaEnum);

    List<Pauta> findAllFinalizadas();

    List<Pauta> findAllNaoIniciadas();

    List<Pauta> findAllEmVotacao();

    void votarEmUmaPauta(CooperadoDTO cooperadoDTO) throws Exception;

    void checarStatus(Pauta pauta, Sessao sessao);

    ResponseEntity cadastrarVotosRealizados(CooperadoDTO cooperadoDTO);

}
