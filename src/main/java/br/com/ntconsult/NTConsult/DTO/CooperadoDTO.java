package br.com.ntconsult.NTConsult.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CooperadoDTO implements Serializable {

    private static final long serialVersionUID = -5810234404065011742L;

    @JsonProperty
    private Long pauta_id;

    @JsonProperty
    private String cpf;

    @JsonProperty
    private String voto;
}
