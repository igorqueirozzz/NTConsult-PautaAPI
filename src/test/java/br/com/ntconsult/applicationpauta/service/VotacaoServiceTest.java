package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.domain.model.VotacoesRealizadas;
import br.com.ntconsult.applicationpauta.domain.repository.VotacoesRealizadasRepository;
import br.com.ntconsult.applicationpauta.dto.CooperadoDTO;
import br.com.ntconsult.applicationpauta.exception.VotoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class VotacaoServiceTest {

    @Autowired
    VotacaoService votacaoService;

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;

    @Test(expected = VotoException.class)
    public void validarVoto(){
        CooperadoDTO cooperadoDTO = new CooperadoDTO();
        cooperadoDTO.setCpf("00000000000");
        cooperadoDTO.setVoto("TESTE");
        cooperadoDTO.setPauta_id(1l);

        votacaoService.validarVoto(cooperadoDTO);
    }

    @Test(expected = VotoException.class)
    public void checarSeJaVotou(){
        VotacoesRealizadas votacoesRealizadas = new VotacoesRealizadas();
        votacoesRealizadas.setCpf_cooperado("00000000000");
        votacoesRealizadas.setId_pauta(1l);
        votacoesRealizadasRepository.save(votacoesRealizadas);

        CooperadoDTO cooperadoDTO = new CooperadoDTO();
        cooperadoDTO.setCpf("00000000000");
        cooperadoDTO.setVoto("sim");
        cooperadoDTO.setPauta_id(1l);

        votacaoService.checarSeJaVotou(cooperadoDTO);
    }
}
