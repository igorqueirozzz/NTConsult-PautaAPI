package br.com.ntconsult.NTConsult.services.implementation;

import br.com.ntconsult.NTConsult.services.PautaService;
import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntconsult.NTConsult.services.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    PautaService pautaService;

    @Override
    public void checarSeJaVotou(CooperadoDTO cooperadoDTO) {

    }
}
