package br.com.ntconsult.NTConsult.services;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;

public interface VotacaoService {

    void validarVoto(CooperadoDTO cooperadoDTO);

    void checarSeJaVotou(CooperadoDTO cooperadoDTO);

}