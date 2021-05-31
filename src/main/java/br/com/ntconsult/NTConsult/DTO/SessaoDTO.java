package br.com.ntconsult.NTConsult.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoDTO implements Serializable {

    private static final long serialVersionUID = 8647113631629350510L;

    @JsonProperty
    private Long pauta_id;
    @JsonProperty
    private Long duracao;
    
}
