package br.com.ntconsult.NTConsult.services;

import br.com.ntconsult.NTConsult.DTO.SessaoDTO;
import org.springframework.http.ResponseEntity;

public interface SessaoManagerService {

    ResponseEntity abrirSessao(SessaoDTO sessaoDTO);
    void sessaoTimeProcessor();
    void finalizarSessao(Long sessao_id);

}
