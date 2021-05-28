package br.com.ntconsult.NTConsult.services;

import org.springframework.http.ResponseEntity;

public interface SessaoService {

    ResponseEntity aplicarSessao(Long pautaid);

}
