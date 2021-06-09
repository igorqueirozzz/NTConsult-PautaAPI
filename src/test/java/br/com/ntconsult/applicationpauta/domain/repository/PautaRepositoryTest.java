package br.com.ntconsult.applicationpauta.domain.repository;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PautaRepositoryTest {

    @Autowired
    PautaRepository pautaRepository;


    @Test
    public void findAllEmVotacao(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste 1");
        pauta.setDescricao("Em votacao");
        pauta.setStatus(StatusPautaEnum.EMVOTACAO);
        pautaRepository.save(pauta);
        List<Pauta> pautas = pautaRepository.findAllEmVotacao();
        Assertions.assertThat(pautas).isNotEmpty();
    }

    @Test
    public void findAllNaoIniciadas(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste 1");
        pauta.setDescricao("Em votacao");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);
        List<Pauta> pautas = pautaRepository.findAllNaoIniciadas();
        Assertions.assertThat(pautas).isNotEmpty();
    }

    @Test
    public void findAllFinalizadas(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste 1");
        pauta.setDescricao("Finalizadas");
        pauta.setStatus(StatusPautaEnum.FINALIZADA);
        pautaRepository.save(pauta);
        List<Pauta> pautas = pautaRepository.findAllFinalizadas();
        Assertions.assertThat(pautas).isNotEmpty();
    }
}
