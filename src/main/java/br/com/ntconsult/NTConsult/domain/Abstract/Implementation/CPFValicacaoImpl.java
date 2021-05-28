package br.com.ntconsult.NTConsult.domain.Abstract.Implementation;

import br.com.ntconsult.NTConsult.domain.Abstract.CPFValidacao;
import br.com.ntconsult.NTConsult.exception.VotoException;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

@Service
public class CPFValicacaoImpl implements CPFValidacao {


    @Override
    public void validarCPF(String cpf) throws IOException {

        String url = "https://user-info.herokuapp.com/users/" + cpf;

        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
                System.out.println("SERVIÇO DE VALIDAÇÃO EXTERNO FALHOU, VOTO COMPUTADO SEM VALIDAR CPF." +  conn.getResponseMessage());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuffer response = new StringBuffer();

            String line = null;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            if (response.toString().contains("UNABLE")){
                throw new VotoException("CPF INVÁLIDO.");
            }
        } catch (ProtocolException e) {
            System.out.println("SISTEMA DE VALIDAÇAO DE CPF EXTERNO FALHOU.");
        }





        conn.disconnect();
    }
}
