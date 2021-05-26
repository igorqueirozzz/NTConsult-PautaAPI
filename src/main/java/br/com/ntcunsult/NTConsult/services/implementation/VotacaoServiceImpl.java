package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.services.PautaService;
import br.com.ntcunsult.NTConsult.services.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@Service
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    PautaService pautaService;

    @Override
    public void checarSeJaVotou(CooperadoDTO cooperadoDTO) {

    }
}
