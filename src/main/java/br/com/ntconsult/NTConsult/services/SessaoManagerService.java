package br.com.ntconsult.NTConsult.services;

import br.com.ntconsult.NTConsult.DTO.SessaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

public interface SessaoManagerService {

    ResponseEntity abrirSessao(SessaoDTO sessaoDTO);

    @Scheduled
    void sessaoTimeProcessor();

    void finalizarSessao(Long sessao_id);

}
