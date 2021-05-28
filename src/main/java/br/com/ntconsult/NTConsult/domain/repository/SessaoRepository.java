package br.com.ntconsult.NTConsult.domain.repository;

import br.com.ntconsult.NTConsult.domain.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

}
