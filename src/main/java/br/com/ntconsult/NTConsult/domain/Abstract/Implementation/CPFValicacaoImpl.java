package br.com.ntconsult.NTConsult.domain.Abstract.Implementation;

import br.com.ntconsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import br.com.ntconsult.NTConsult.domain.Abstract.CPFValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CPFValicacaoImpl implements CPFValidacao {

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;


    @Override
    public void validarCPF(String cpf) throws Exception{

        String url = "https://user-info.herokuapp.com/users/" + cpf;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuffer response = new StringBuffer();

        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        if (response.toString().contains(""));

        System.out.println(response.toString());

        conn.disconnect();
    }
}
