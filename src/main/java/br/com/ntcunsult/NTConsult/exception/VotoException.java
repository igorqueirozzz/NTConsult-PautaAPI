package br.com.ntcunsult.NTConsult.exception;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.domain.model.VotacoesRealizadas;
import br.com.ntcunsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class VotoException extends RuntimeException{

    @Autowired
    private VotacoesRealizadasRepository repository;

    public VotoException(String message){
        super(message);
    }

    public static void validarVoto(CooperadoDTO cooperadoDTO){
        boolean isValid = true;
        if(cooperadoDTO.getVoto().equalsIgnoreCase("sim")){

        }else if (cooperadoDTO.getVoto().equalsIgnoreCase("nao")){

        }else if (cooperadoDTO.getVoto().equalsIgnoreCase("não")){

        }else {
            throw new VotoException("SOMENTE SÃO ACEITOS VOTOS EM FORMATO DE TEXTO CONTENDO 'SIM' OU 'NAO'. ");
        }
    }


}
