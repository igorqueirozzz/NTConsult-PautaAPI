package br.com.ntcunsult.NTConsult.services;

import br.com.ntcunsult.NTConsult.DTO.SessaoDTO;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import org.hibernate.internal.SessionOwnerBehavior;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface SessaoManagerService {

    ResponseEntity abrirSessao(SessaoDTO sessaoDTO);
    void sessaoTimeProcessor();
    void finalizarSessao(Long sessao_id);

}
