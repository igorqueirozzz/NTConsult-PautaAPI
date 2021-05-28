package br.com.ntconsult.NTConsult.domain.repository;

import br.com.ntconsult.NTConsult.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Query(value = "SELECT * FROM NT_PAUTAS WHERE NT_PAUTAS.STATUS = 'FINALIZADA'", nativeQuery = true)
    List<Pauta> findAllFinalizadas();

    @Query(value = "SELECT * FROM NT_PAUTAS WHERE NT_PAUTAS.STATUS = 'NAOREALIZADA'", nativeQuery = true)
    List<Pauta> findAllNaoIniciadas();

    @Query(value = "SELECT * FROM NT_PAUTAS WHERE NT_PAUTAS.STATUS = 'EMVOTACAO'", nativeQuery = true)
    List<Pauta> findAllEmVotacao();


}
