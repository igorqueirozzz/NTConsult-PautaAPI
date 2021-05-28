package br.com.ntconsult.NTConsult.domain.model;

import br.com.ntconsult.NTConsult.domain.enumeration.StatusPautaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "NT_PAUTAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAUTA")
    @JsonProperty
    private Long id_pauta;

    @Column(name = "ASSUNTO", nullable = false)
    @JsonProperty
    private String assunto;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    @JsonProperty
    private String descricao;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StatusPautaEnum status;

    @Column(name = "VOTOS_SIM", nullable = true)
    @JsonProperty
    private Integer votos_sim;

    @Column(name = "VOTOS_NAO", nullable = true)
    @JsonProperty
    private Integer votos_nao;

    @ManyToOne
    @JoinColumn(name = "ID_SESSAO", nullable = true)
    @JsonProperty
    private Sessao sessao;


}
