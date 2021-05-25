package br.com.ntcunsult.NTConsult.services;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import org.springframework.http.ResponseEntity;


public interface PautaService {

    ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception;

}
