package br.com.ntconsult.applicationpauta.domain.model;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusPautaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public",name = "nt_pautas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pauta implements Serializable {

    private static final long serialVersionUID = -7729941434043009538L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pauta")
    @JsonProperty
    private Long id_pauta;

    @Column(name = "assunto", nullable = false)
    @JsonProperty
    private String assunto;

    @Column(name = "descricao", length = 100, nullable = false)
    @JsonProperty
    private String descricao;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StatusPautaEnum status;

    @Column(name = "votos_sim")
    @JsonProperty
    private Integer votos_sim;

    @Column(name = "votos_nao")
    @JsonProperty
    private Integer votos_nao;

    @ManyToOne
    @JoinColumn(name = "id_sessao")
    @JsonProperty
    private Sessao sessao;


}
