package br.com.ntcunsult.NTConsult.domain.Abstract;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.domain.repository.SessaoRepository;
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

    public ResponseEntity validar(Pauta pauta) throws Exception {
        try {
            validarExistencia(pauta);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            throw e;
        }
    }

    public ResponseEntity validarExistencia(Pauta pauta) throws Exception {
        boolean pautaJaCadastrada = pautaRepository.exists(Example.of(pauta));
        if (pautaJaCadastrada) {
            throw new Exception("ESSA PAUTA JÁ FOI CADASTRADA ANTERIORMENTE, CONSULTE COM O SEGUINTE ID: " +
                    pautaRepository.findOne(Example.of(pauta)).get().getId_pauta());
        } else {
            return ResponseEntity.ok().build();
        }

    }
}
