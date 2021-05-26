package br.com.ntcunsult.NTConsult.services.implementation;

import br.com.ntcunsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.domain.model.Sessao;
import br.com.ntcunsult.NTConsult.domain.repository.PautaRepository;
import br.com.ntcunsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntcunsult.NTConsult.services.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Override
    public ResponseEntity aplicarSessao(Long pautaid, Long tempoSessao) {
        Sessao sessao = new Sessao();
        sessao.setStatus_sessao(StatusSessaoEnum.NAOINICIADA);
        sessao.setPauta_id(pautaid);
        sessao.setDuracao(100000L);
        Pauta pautaAlterar = new Pauta();
        if (tempoSessao != null && tempoSessao >= 100000L){
            sessao.setDuracao(tempoSessao);
        }
        try {
            sessaoRepository.save(sessao);
            Optional<Pauta> pauta = pautaRepository.findById(pautaid);

            if (pauta != null){
                pautaAlterar = pauta.get();
                pautaAlterar.setSessao(sessao);
            }else {
                throw new Exception("NÃO FOI POSSÍVEL APLICAR A SESSÃO NA PAUTA.");
            }
            pautaRepository.save(pautaAlterar);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(pautaAlterar);
    }
}
