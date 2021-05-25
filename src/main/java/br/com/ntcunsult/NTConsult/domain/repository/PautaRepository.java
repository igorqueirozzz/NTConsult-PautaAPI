package br.com.ntcunsult.NTConsult.domain.repository;

import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
