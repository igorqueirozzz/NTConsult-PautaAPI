package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.dto.CooperadoDTO;

public interface VotacaoService {

    void validarVoto(CooperadoDTO cooperadoDTO);

    void checarSeJaVotou(CooperadoDTO cooperadoDTO);

}
