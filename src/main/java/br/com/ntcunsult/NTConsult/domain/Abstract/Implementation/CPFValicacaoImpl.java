package br.com.ntcunsult.NTConsult.domain.Abstract.Implementation;

import br.com.ntcunsult.NTConsult.domain.Abstract.CPFValidacao;
import br.com.ntcunsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CPFValicacaoImpl implements CPFValidacao {

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;


    @Override
    public void validarCPF(String cpf) {
//        votacoesRealizadasRepository.existsBycpf_cooperado(cpf);
    }
}
