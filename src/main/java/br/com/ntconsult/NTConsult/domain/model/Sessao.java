package br.com.ntconsult.NTConsult.domain.model;

import br.com.ntconsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "NT_SESSAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sessao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_SESSAO")
    @JsonProperty
    private Long id_sessao;

    @Column(name = "PAUTA_ID")
    @JsonProperty
    private Long pauta_id;

    @Column(name = "DURACAO")
    @JsonProperty
    private Long duracao;

    @Column(name = "FINAL_DA_SESSAO")
    @JsonProperty
    private LocalTime final_sessao;

    @Column(name = "STATUS_SESSAO")
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StatusSessaoEnum status_sessao;
}
