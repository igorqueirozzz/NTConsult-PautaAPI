package br.com.ntconsult.applicationpauta.domain.Abstract.Implementation;

import br.com.ntconsult.applicationpauta.domain.Abstract.CPFValidacao;
import br.com.ntconsult.applicationpauta.exception.VotoException;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CPFValicacaoImpl implements CPFValidacao {


    @Override
    public void validarCPF(String cpf) throws Exception {
        try {
            validacaoExterna(cpf);
        } catch (Exception e){
            System.out.println("SISTEMA DE VALIDAÇÃO EXTERNA FALHOU O VOTO SERÁ COMPUTADO SEM VALIDAÇÃO DO CPF.");
            throw new VotoException("SISTEMA DE VALIDAÇÃO EXTERNA FALHOU O VOTO SERÁ COMPUTADO SEM VALIDAÇÃO DO CPF.");
        }

    }

    @Override
    public void validacaoExterna(String cpf) throws Exception {
        String url = "https://user-info.herokuapp.com/users/" + cpf;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if (conn.getResponseCode() != 200) {
            System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            throw new VotoException("SISTEMA DE VALIDAÇÃO EXTERNA FALHOU, O VOTO SERÁ COMPUTADO SEM VALIDAÇÃO.");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuffer response = new StringBuffer();

        String line = null;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        if (response.toString().contains("UNABLE")){ throw new VotoException("CPF INVÁLIDO.");
        }
        conn.disconnect();
    }
}
