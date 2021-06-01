package br.com.ntconsult.applicationpauta.domain.Abstract.Implementation;

import br.com.ntconsult.applicationpauta.domain.Abstract.PautaValidacao;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.repository.PautaRepository;
import br.com.ntconsult.applicationpauta.exception.PautaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PautaValidacaoImpl implements PautaValidacao {

    @Autowired
    PautaRepository pautaRepository;

    public void validar(Pauta pauta){
        try {
            validarExistencia(pauta);
        } catch (PautaException e) {
            throw e;
        }
    }

    public boolean validarExistencia(Pauta pauta){
        boolean pautaJaCadastrada = pautaRepository.exists(Example.of(pauta));

        if (pautaJaCadastrada) {
            throw new PautaException("ESSA PAUTA JÁ FOI CADASTRADA ANTERIORMENTE, CONSULTE COM O SEGUINTE ID: " +
                    pautaRepository.findOne(Example.of(pauta)).get().getId_pauta());
        }
        return pautaJaCadastrada;
    }
}
