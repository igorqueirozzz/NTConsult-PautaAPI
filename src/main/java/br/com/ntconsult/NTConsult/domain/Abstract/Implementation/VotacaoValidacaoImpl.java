package br.com.ntconsult.NTConsult.domain.Abstract.Implementation;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntconsult.NTConsult.domain.Abstract.VotacaoValidacao;
import br.com.ntconsult.NTConsult.domain.model.VotacoesRealizadas;
import br.com.ntconsult.NTConsult.domain.repository.VotacoesRealizadasRepository;
import br.com.ntconsult.NTConsult.exception.VotoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotacaoValidacaoImpl implements VotacaoValidacao {

    @Autowired
    VotacoesRealizadasRepository votacoesRealizadasRepository;

    @Override
    public void validarVoto(CooperadoDTO cooperadoDTO) {

        String votoCooperado = cooperadoDTO.getVoto();

        if(!votoCooperado.trim().equalsIgnoreCase("sim")
        && !votoCooperado.trim().equalsIgnoreCase("nao")
        && !votoCooperado.trim().equalsIgnoreCase("não")){
            throw new VotoException("VOCÊ SÓ PODE VOTAR COM 'SIM' OU 'NAO'");
        }
    }

    @Override
    public void checarSeJaVotou(CooperadoDTO cooperadoDTO) {
        Optional<VotacoesRealizadas> votacoesRealizadas = votacoesRealizadasRepository.findByid_pauta(cooperadoDTO.getPauta_id(), cooperadoDTO.getCpf());
        if (!votacoesRealizadas.isEmpty()){
            throw new VotoException("VOCÊ JÁ VOTOU NESSA PAUTA.");
        }
    }


}
