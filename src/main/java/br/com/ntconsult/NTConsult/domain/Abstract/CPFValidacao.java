package br.com.ntconsult.NTConsult.domain.Abstract;

import java.net.MalformedURLException;
import java.net.ProtocolException;

public interface CPFValidacao {
    void validarCPF(String cpf) throws ProtocolException, MalformedURLException, Exception;
}
