package br.com.ntconsult.NTConsult.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PautaDTO implements Serializable {

    @JsonProperty
    private String assunto;

    @JsonProperty
    private String descricao;
}
