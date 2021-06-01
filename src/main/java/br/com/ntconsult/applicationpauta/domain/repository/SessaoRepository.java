package br.com.ntconsult.applicationpauta.domain.repository;

import br.com.ntconsult.applicationpauta.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query(value = "SELECT * FROM NT_SESSAO WHERE NT_SESSAO.PAUTA_ID = ?", nativeQuery = true)
    Optional<Sessao> findByPautaId(Long Long);
}
