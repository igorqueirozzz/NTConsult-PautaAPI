package br.com.ntcunsult.NTConsult.DTO;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;

@ManagedBean
@Data
public class PautaDTO {

    private Pauta pauta;

    private Long tempo_sessao;
}
