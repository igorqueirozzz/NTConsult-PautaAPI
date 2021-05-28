package br.com.ntconsult.NTConsult.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "NT_COOPERADOS")
public class Cooperado implements Serializable {
    @Id
    @Column(name = "ID_COOPERADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id_cooperado;

    @Column(name = "CPF_COOPERADO", length = 11, nullable = false)
    @JsonProperty
    private String cpf_cooperado;

}
