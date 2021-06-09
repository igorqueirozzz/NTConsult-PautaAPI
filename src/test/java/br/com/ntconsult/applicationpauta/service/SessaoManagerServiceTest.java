package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.enumeration.StatusSessaoEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.model.Sessao;
import br.com.ntconsult.applicationpauta.domain.repository.PautaRepository;
import br.com.ntconsult.applicationpauta.domain.repository.SessaoRepository;
import br.com.ntconsult.applicationpauta.dto.SessaoDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SessaoManagerServiceTest {

    @Autowired
    SessaoManagerService sessaoManagerService;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Test
    public void abrirSessao(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);

        Sessao sessao = new Sessao();
        sessao.setStatus_sessao(StatusSessaoEnum.NAOINICIADA);
        sessao.setPauta_id(pauta.getId_pauta());
        sessaoRepository.save(sessao);

        SessaoDTO sessaoDTO = new SessaoDTO();
        sessaoDTO.setDuracao(5l);
        sessaoDTO.setPauta_id(pauta.getId_pauta());
        ResponseEntity responseEntity = sessaoManagerService.abrirSessao(sessaoDTO);

        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void finalizarSessao(){
        Sessao sessao = new Sessao();
        sessao.setStatus_sessao(StatusSessaoEnum.EMVOTACAO);
        sessao.setPauta_id(1l);
        sessaoRepository.save(sessao);

        sessaoManagerService.finalizarSessao(sessao.getId_sessao());
        Optional<Sessao> sessaoOptional = sessaoRepository.findById(sessao.getId_sessao());
        Sessao sessaoFinalizada = sessaoOptional.get();

        Assertions.assertThat(sessaoFinalizada.getStatus_sessao()).isEqualTo(StatusSessaoEnum.FINALIZADA);
    }


}
