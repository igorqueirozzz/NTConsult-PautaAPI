package br.com.ntcunsult.NTConsult.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "NT_COOPERADOS")
public class Cooperado {
    @Id
    @Column(name = "ID_COOPERADO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cooperado;

    @Column(name = "CPF_COOPERADO", length = 11, nullable = false)
    private String cpf_cooperado;

}
