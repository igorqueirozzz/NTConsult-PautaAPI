package br.com.ntconsult.applicationpauta.domain.Abstract;

public interface CPFValidacao {

    void validarCPF(String cpf) throws Exception;

    void validacaoExterna(String cpf) throws Exception;
}
