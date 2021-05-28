package br.com.ntconsult.NTConsult.services;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntconsult.NTConsult.DTO.PautaDTO;
import br.com.ntconsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.NTConsult.domain.model.Pauta;
import br.com.ntconsult.NTConsult.domain.model.Sessao;
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

    void cadastrarVotosRealizados(CooperadoDTO cooperadoDTO);

}
