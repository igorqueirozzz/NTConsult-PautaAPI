package br.com.ntconsult.NTConsult.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "NT_VOTACOES_REALIZADAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotacoesRealizadas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id_votacoes_realizadas;

    @Column(name = "ID_PAUTA", nullable = false)
    @JsonProperty
    private Long id_pauta;

    @Column(name = "CPF_COOPERADO", nullable = false)
    @JsonProperty
    private String cpf_cooperado;



}
