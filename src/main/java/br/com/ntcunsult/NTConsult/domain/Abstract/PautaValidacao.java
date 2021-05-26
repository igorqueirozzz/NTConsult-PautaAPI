package br.com.ntcunsult.NTConsult.domain.Abstract;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import org.springframework.http.ResponseEntity;

public interface PautaValidacao {
    void validar(Pauta pauta) throws Exception;
    void validarExistencia(Pauta pauta) throws Exception;
}
