package br.com.ntconsult.applicationpauta.domain.Abstract;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.repository.PautaRepository;
import br.com.ntconsult.applicationpauta.exception.PautaException;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PautaValidacaoTest {

    @Autowired
    PautaRepository pautaRepository;
    @Autowired
    PautaValidacao pautaValidacao;

    @Test(expected = PautaException.class)
    public void validar(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("Teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);
        pautaValidacao.validar(pauta);
    }

    @Test
    public void validarExistencia(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("Teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);
        boolean existe = pautaRepository.exists(Example.of(pauta));
        Assertions.assertThat(existe).isTrue();
    }
}
