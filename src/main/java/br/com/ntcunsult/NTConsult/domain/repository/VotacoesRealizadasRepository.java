package br.com.ntcunsult.NTConsult.domain.repository;

import br.com.ntcunsult.NTConsult.domain.model.Cooperado;
import br.com.ntcunsult.NTConsult.domain.model.VotacoesRealizadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotacoesRealizadasRepository extends JpaRepository<VotacoesRealizadas, Long> {

    @Query(value = "SELECT * FROM NT_VOTACOES_REALIZADAS WHERE NT_VOTACOES_REALIZADAS.ID_PAUTA = ? AND NT_VOTACOES_REALIZADAS.CPF_COOPERADO = ?", nativeQuery = true)
    Optional<VotacoesRealizadas> findByid_pauta(Long pautaid, String cpf);

}
