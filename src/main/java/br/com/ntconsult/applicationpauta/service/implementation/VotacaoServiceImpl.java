package br.com.ntconsult.applicationpauta.service.implementation;

import br.com.ntconsult.applicationpauta.domain.model.VotacoesRealizadas;
import br.com.ntconsult.applicationpauta.domain.repository.VotacoesRealizadasRepository;
import br.com.ntconsult.applicationpauta.exception.VotoException;
import br.com.ntconsult.applicationpauta.dto.CooperadoDTO;
import br.com.ntconsult.applicationpauta.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;

    @Override
    public void validarVoto(CooperadoDTO cooperadoDTO) {

        String votoCooperado = cooperadoDTO.getVoto();

        if(!votoCooperado.trim().equalsIgnoreCase("sim")
                && !votoCooperado.trim().equalsIgnoreCase("nao")
                && !votoCooperado.trim().equalsIgnoreCase("não")){
            throw new VotoException("SÃO ACEITOS SOMENTE VOTOS 'SIM' OU 'NAO'");
        }
    }

    @Override
    public void checarSeJaVotou(CooperadoDTO cooperadoDTO) {
        Optional<VotacoesRealizadas> votacoesRealizadas = votacoesRealizadasRepository.findByPautaAndCpf(cooperadoDTO.getPauta_id(), cooperadoDTO.getCpf());
        if (votacoesRealizadas.isPresent()){
            throw new VotoException("VOCÊ JÁ VOTOU NESSA PAUTA.");
        }
    }
}
