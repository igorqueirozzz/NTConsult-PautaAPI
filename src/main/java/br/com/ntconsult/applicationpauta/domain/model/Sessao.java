package br.com.ntconsult.applicationpauta.domain.model;

import br.com.ntconsult.applicationpauta.domain.enumeration.StatusSessaoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(schema = "public", name = "nt_sessao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sessao implements Serializable {

    private static final long serialVersionUID = -2937089804507072569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sessao")
    @JsonProperty
    private Long id_sessao;

    @Column(name = "pauta_id")
    @JsonProperty
    private Long pauta_id;

    @Column(name = "duracao")
    @JsonProperty
    private Long duracao;

    @Column(name = "final_sessao")
    @JsonProperty
    private LocalTime final_sessao;

    @Column(name = "status_sessao")
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StatusSessaoEnum status_sessao;
}
