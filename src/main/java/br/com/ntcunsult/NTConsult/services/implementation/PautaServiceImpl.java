package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.domain.Abstract.PautaValidacao;
import br.com.ntcunsult.NTConsult.domain.enumeration.ResultadoEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PautaServiceImpl implements PautaService {

    @Autowired
    PautaValidacao pautaValidacao;

    @Autowired
    PautaRepository pautaRepository;

    @Override
    public ResponseEntity cadastrarPauta(PautaDTO pautaDTO) throws Exception {
        Pauta pauta = pautaDTO.getPauta();
        pauta.setResultado(ResultadoEnum.NAOVOTADA);
        pauta.setStatus(StatusPautaEnum.NAOREALIZADA);
        pautaValidacao.validar(pauta);
       // pauta.setSessao(aplicarSessao(pauta.getId_pauta(), pautaDTO.getTempo_sessao()));
        pautaRepository.save(pauta);
        return ResponseEntity.ok("Pauta Nº" + pautaDTO.getPauta().getId_pauta() + " cadastrada com sucesso!");
    }

    public Sessao aplicarSessao(Long pautaId, Long tempoSessao){
        Sessao sessao = new Sessao();
        sessao.setStatus_sessao(StatusSessaoEnum.NAOINICIADA);
        sessao.setDuracao(tempoSessao);
        sessao.setPauta_id(pautaId);
        return sessao;
    }
}
