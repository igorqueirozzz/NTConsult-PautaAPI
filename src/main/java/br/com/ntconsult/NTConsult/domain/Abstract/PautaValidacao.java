package br.com.ntconsult.NTConsult.domain.Abstract;

import br.com.ntconsult.NTConsult.domain.model.Pauta;

public interface PautaValidacao {
    void validar(Pauta pauta) throws Exception;
    void validarExistencia(Pauta pauta) throws Exception;
}
