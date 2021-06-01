package br.com.ntconsult.applicationpauta.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "nt_votacoes_realizadas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotacoesRealizadas implements Serializable {

    private static final long serialVersionUID = 3252086413252073984L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_votacoes_realizadas")
    @JsonProperty
    private Long id_votacoes_realizadas;

    @Column(name = "id_pauta", nullable = false)
    @JsonProperty
    private Long id_pauta;

    @Column(name = "cpf_cooperado", nullable = false)
    @JsonProperty
    private String cpf_cooperado;



}
