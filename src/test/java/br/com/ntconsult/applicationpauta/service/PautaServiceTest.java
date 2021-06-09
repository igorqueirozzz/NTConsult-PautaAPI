package br.com.ntconsult.applicationpauta.service;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.applicationpauta.domain.enumeration.StatusSessaoEnum;
import br.com.ntconsult.applicationpauta.domain.model.Pauta;
import br.com.ntconsult.applicationpauta.domain.model.Sessao;
import br.com.ntconsult.applicationpauta.domain.repository.PautaRepository;
import br.com.ntconsult.applicationpauta.domain.repository.SessaoRepository;
import br.com.ntconsult.applicationpauta.dto.CooperadoDTO;
import br.com.ntconsult.applicationpauta.dto.PautaDTO;
import br.com.ntconsult.applicationpauta.exception.PautaException;
import br.com.ntconsult.applicationpauta.exception.VotoException;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class PautaServiceTest {

    @Autowired
    PautaService pautaService;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoRepository sessaoRepository;

    @Test
    public void cadastrarPauta() throws Exception {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setAssunto("Teste");
        pautaDTO.setDescricao("Teste");
        ResponseEntity responseEntity = pautaService.cadastrarPauta(pautaDTO);

        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void alterarStatus(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);
        pautaService.alterarStatus(pauta.getId_pauta(), StatusPautaEnum.EMVOTACAO);
        Optional<Pauta> pautaAlteradaOptional = pautaRepository.findById(pauta.getId_pauta());
       Pauta pautaAlterada = pautaAlteradaOptional.get();
       Assertions.assertThat(pautaAlterada.getStatus()).isEqualTo(StatusPautaEnum.EMVOTACAO);
    }

    @Test(expected = VotoException.class)
    public void votarEmUmaPauta() throws Exception {
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.EMVOTACAO);
        pautaRepository.save(pauta);

        CooperadoDTO cooperadoDTO = new CooperadoDTO();
        cooperadoDTO.setPauta_id(pauta.getId_pauta());
        cooperadoDTO.setCpf("00000000000");
        cooperadoDTO.setVoto("sim");

        pautaService.votarEmUmaPauta(cooperadoDTO);
    }

    @Test(expected = PautaException.class)
    public void checarStatus(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaRepository.save(pauta);

        Sessao sessao = new Sessao();
        sessao.setPauta_id(pauta.getId_pauta());
        sessao.setStatus_sessao(StatusSessaoEnum.NAOINICIADA);
        sessao.setDuracao(1l);
        sessaoRepository.save(sessao);
        pauta.setSessao(sessao);
        pautaRepository.save(pauta);

        pautaService.checarStatus(pauta, sessao);
    }

    @Test
    public void cadastrarVotosRealizados(){
        Pauta pauta = new Pauta();
        pauta.setAssunto("Teste");
        pauta.setDescricao("teste");
        pauta.setStatus(StatusPautaEnum.EMVOTACAO);
        pautaRepository.save(pauta);

        CooperadoDTO cooperadoDTO = new CooperadoDTO();
        cooperadoDTO.setPauta_id(pauta.getId_pauta());
        cooperadoDTO.setCpf("00000000000");
        cooperadoDTO.setVoto("sim");

        ResponseEntity responseEntity = pautaService.cadastrarVotosRealizados(cooperadoDTO);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

}
