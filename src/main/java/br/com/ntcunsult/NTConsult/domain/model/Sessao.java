package br.com.ntcunsult.NTConsult.domain.model;

import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "NT_SESSAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sessao {

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

    @Column(name = "TEMPO_RESTANTE")
    @JsonProperty
    private Long tempo_restante;

    @Column(name = "STATUS_SESSAO")
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StatusSessaoEnum status_sessao;
}
