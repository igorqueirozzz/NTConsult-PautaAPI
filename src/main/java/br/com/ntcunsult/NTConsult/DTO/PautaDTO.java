package br.com.ntcunsult.NTConsult.DTO;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
