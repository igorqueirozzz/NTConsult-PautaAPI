package br.com.ntconsult.applicationpauta.domain.Abstract;

import br.com.ntconsult.applicationpauta.domain.model.Pauta;

public interface PautaValidacao {

    void validar(Pauta pauta);

    boolean validarExistencia(Pauta pauta);
}
