package br.com.ntcunsult.NTConsult.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoDTO implements Serializable {
    @JsonProperty
    private Long pauta_id;
    @JsonProperty
    private Long duracao;
    
}
