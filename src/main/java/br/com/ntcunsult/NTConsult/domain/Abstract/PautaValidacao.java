package br.com.ntcunsult.NTConsult.domain.Abstract;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import org.springframework.http.ResponseEntity;

public interface PautaValidacao {
    ResponseEntity validar(Pauta pauta) throws Exception;
    ResponseEntity validarExistencia(Pauta pauta) throws Exception;
}
