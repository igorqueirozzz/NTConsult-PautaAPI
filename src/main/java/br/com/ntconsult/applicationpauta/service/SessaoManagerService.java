package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.dto.SessaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

public interface SessaoManagerService {

    ResponseEntity abrirSessao(SessaoDTO sessaoDTO);

    @Scheduled
    void sessaoTimeProcessor();

    void finalizarSessao(Long sessao_id);

}
