package br.com.ntcunsult.NTConsult.services;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessaoService {

    ResponseEntity aplicarSessao(Long pautaid);


}
