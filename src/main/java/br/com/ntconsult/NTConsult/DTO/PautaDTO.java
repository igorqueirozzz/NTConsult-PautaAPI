package br.com.ntconsult.NTConsult.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.ManagedBean;
import java.io.Serializable;

@ManagedBean
@Data
public class PautaDTO implements Serializable {

    @JsonProperty
    private String assunto;

    @JsonProperty
    private String descricao;
}
