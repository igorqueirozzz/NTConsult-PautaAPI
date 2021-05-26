package br.com.ntcunsult.NTConsult.exception;

public class VotoException extends RuntimeException{

    public VotoException(String message){
        super(message);
    }

    public static boolean validarVoto(String voto){
        boolean isValid = true;
        if(voto.equals("sim")){
            isValid = true;
        }else if (voto.equalsIgnoreCase("nao")){
            isValid = true;
        }else if (voto.equalsIgnoreCase("não")){
            isValid = true;
        }else {
            throw new VotoException("SOMENTE SÃO ACEITOS VOTOS EM FORMATO DE TEXTO CONTENDO 'SIM' OU 'NAO'. ");
        }
        return isValid;
    }

}
