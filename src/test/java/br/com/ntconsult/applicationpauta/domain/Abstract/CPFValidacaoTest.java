package br.com.ntconsult.applicationpauta.domain.Abstract;

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
public class CPFValidacaoTest {

    @Autowired
    CPFValidacao cpfValidacao;

    @Test(expected = VotoException.class)
    public void validacaoExterna() throws Exception {
        cpfValidacao.validacaoExterna("214564578");
    }
}
