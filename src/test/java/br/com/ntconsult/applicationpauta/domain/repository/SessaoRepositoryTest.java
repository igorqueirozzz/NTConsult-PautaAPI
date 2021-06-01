package br.com.ntconsult.applicationpauta.domain.repository;

import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.model.Sessao;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SessaoRepositoryTest {

    @Autowired
    SessaoRepository sessaoRepository;

    @Test
    public void findByPautaId(){
        Sessao sessaoTest = new Sessao();
        sessaoTest.setPauta_id(1L);
        sessaoRepository.save(sessaoTest);
        Optional<Sessao> sessao = sessaoRepository.findByPautaId( 1L);
        Assertions.assertThat(sessao).isPresent();
    }

}
