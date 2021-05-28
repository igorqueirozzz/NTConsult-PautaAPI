package br.com.ntconsult.NTConsult.domain.Abstract;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;

public interface VotacaoValidacao {

    void validarVoto(CooperadoDTO cooperadoDTO);

    void checarSeJaVotou(CooperadoDTO cooperadoDTO);


}
