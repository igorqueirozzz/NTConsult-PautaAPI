package br.com.ntcunsult.NTConsult.domain.Abstract;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;

public interface VotacaoValidacao {

    void validarVoto(CooperadoDTO cooperadoDTO);

    void checarSeJaVotou(CooperadoDTO cooperadoDTO);


}
