package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.repository.PautaRepository;
import br.com.ntconsult.applicationpauta.domain.repository.SessaoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class SessaoServiceTest {

    @Autowired
    SessaoService sessaoService;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Test
    public void aplicarSessao(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);

        ResponseEntity responseEntity = sessaoService.aplicarSessao(pauta.getId_pauta());

        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
