package br.com.ntconsult.applicationpauta.domain.repository;

import br.com.ntconsult.applicationpauta.domain.model.VotacoesRealizadas;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VotacoesRealizadasRepositoryTest {

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;

    @Test
    public void findByPautaAndCpf(){
        VotacoesRealizadas votacoesRealizadasTeste = new VotacoesRealizadas();
        votacoesRealizadasTeste.setId_pauta(1L);
        votacoesRealizadasTeste.setCpf_cooperado("00000000000");
        votacoesRealizadasRepository.save(votacoesRealizadasTeste);
        Optional<VotacoesRealizadas> votacoesRealizadas = votacoesRealizadasRepository.findByPautaAndCpf(1L, "00000000000");
        Assertions.assertThat(votacoesRealizadas).isPresent();
    }
}
