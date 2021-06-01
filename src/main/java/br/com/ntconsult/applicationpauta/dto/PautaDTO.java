package br.com.ntconsult.applicationpauta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PautaDTO implements Serializable {

    private static final long serialVersionUID = 3764124644569942785L;

    @JsonProperty
    private String assunto;

    @JsonProperty
    private String descricao;
}
