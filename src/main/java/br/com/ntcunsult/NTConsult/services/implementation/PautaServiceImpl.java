package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntcunsult.NTConsult.domain.enumeration.ResultadoEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntcunsult.NTConsult.services.PautaService;
import br.com.ntcunsult.NTConsult.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    PautaValidacao pautaValidacao;

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    SessaoService sessaoService;

    @Override
    public ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception {
        Pauta pauta = pautaDTO.getPauta();
        pauta.setResultado(ResultadoEnum.NAOVOTADA);
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaValidacao.validar(pauta);
        pautaRepository.save(pauta);
        sessaoService.aplicarSessao(pautaDTO.getPauta().getId_pauta(), pautaDTO.getTempo_sessao());
        return ResponseEntity.ok("Pauta Nº" + pautaDTO.getPauta().getId_pauta() + " cadastrada com sucesso!");
    }

}
