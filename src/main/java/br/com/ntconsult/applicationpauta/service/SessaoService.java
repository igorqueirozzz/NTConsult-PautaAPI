package br.com.ntconsult.applicationpauta.service;

import org.springframework.http.ResponseEntity;

public interface SessaoService {

    ResponseEntity aplicarSessao(Long pautaid);

}
