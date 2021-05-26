package br.com.ntcunsult.NTConsult.domain.Abstract.Implementation;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntcunsult.NTConsult.exception.PautaException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PautaValidacaoImpl implements PautaValidacao {

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoRepository sessaoRepository;

    public void validar(Pauta pauta) throws Exception {
        try {
            validarExistencia(pauta);
        } catch (PautaException e) {
            throw e;
        }
    }

    public void validarExistencia(Pauta pauta){
        boolean pautaJaCadastrada = pautaRepository.exists(Example.of(pauta));
        if (pautaJaCadastrada) {
            throw new PautaException("ESSA PAUTA JÁ FOI CADASTRADA ANTERIORMENTE, CONSULTE COM O SEGUINTE ID: " +
                    pautaRepository.findOne(Example.of(pauta)).get().getId_pauta());
        }
    }
}
