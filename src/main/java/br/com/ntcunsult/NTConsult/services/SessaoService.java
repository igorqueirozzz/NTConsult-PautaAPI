package br.com.ntcunsult.NTConsult.services;

import org.springframework.http.ResponseEntity;

public interface SessaoService {

    ResponseEntity aplicarSessao(Long pautaid, Long tempoSessao);
}
