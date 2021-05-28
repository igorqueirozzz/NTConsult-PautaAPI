package br.com.ntconsult.NTConsult.domain.Abstract;

import br.com.ntconsult.NTConsult.domain.model.Pauta;

public interface PautaValidacao {

    void validar(Pauta pauta);

    void validarExistencia(Pauta pauta);
}
