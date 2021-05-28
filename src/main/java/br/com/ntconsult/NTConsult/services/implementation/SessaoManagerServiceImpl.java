package br.com.ntconsult.NTConsult.services.implementation;

import br.com.ntconsult.NTConsult.DTO.SessaoDTO;
import br.com.ntconsult.NTConsult.domain.enumeration.StatusPautaEnum;
import br.com.ntconsult.NTConsult.domain.enumeration.StatusSessaoEnum;
import br.com.ntconsult.NTConsult.domain.repository.SessaoRepository;
import br.com.ntconsult.NTConsult.services.PautaService;
import br.com.ntconsult.NTConsult.domain.model.Sessao;
import br.com.ntconsult.NTConsult.exception.SessaoException;
import br.com.ntconsult.NTConsult.services.SessaoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
public class SessaoManagerServiceImpl implements SessaoManagerService {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    PautaService pautaService;

    private static Map<Long, LocalTime> sessoesEmVotacao = new HashMap<Long, LocalTime>();

    @Override
    public ResponseEntity abrirSessao(SessaoDTO sessaoDTO) {
        if (sessaoDTO.getPauta_id() == null){
            throw new SessaoException("INFORME O ID DA SESSÃO!");
        }

       Optional<Sessao> sessao = sessaoRepository.findById(sessaoDTO.getPauta_id());

       if (sessao.isEmpty()){
           throw new SessaoException("SESSÃO NÃO ENCONTRADA NA BASE DE DADOS.");
       }

       Sessao sessaoAbrir = sessao.get();

       if (sessaoAbrir.getStatus_sessao().equals(StatusSessaoEnum.EMVOTACAO)){
           throw new SessaoException("ESTA SESSÃO JÁ ESTÁ EM VOTAÇÃO.");
       } else if(sessaoAbrir.getStatus_sessao().equals(StatusSessaoEnum.FINALIZADA)){
           throw new SessaoException("ESTA SESSÃO JÁ FOI FINALIZADA.");
       }

       sessaoAbrir = sessao.get();
       sessaoAbrir.setDuracao(sessaoDTO.getDuracao());
       sessaoAbrir.setStatus_sessao(StatusSessaoEnum.EMVOTACAO);
       sessaoAbrir.setFinal_sessao(LocalTime.now().plusMinutes(sessao.get().getDuracao()));
       sessaoRepository.save(sessaoAbrir);
       timeSessaoManager(sessaoAbrir);
       pautaService.alterarStatus(sessaoAbrir.getPauta_id(), StatusPautaEnum.EMVOTACAO);
       System.out.println("INFO: SESSÃO Nº" + sessaoAbrir.getId_sessao() + " ABERTA PARA VOTAÇÃO, DURAÇÃO: " + sessaoAbrir.getDuracao() + " MINUTOS");
       return ResponseEntity.ok("SESSÃO Nº" + sessaoAbrir.getId_sessao() + " ABERTA PARA VOTAÇÃO, DURAÇÃO: " + sessaoAbrir.getDuracao() + " MINUTOS");
    }

    void timeSessaoManager(Sessao sessao){
        LocalTime horaDeFinalizar = LocalTime.now().plusMinutes(sessao.getDuracao());
        sessoesEmVotacao.put(sessao.getId_sessao(), horaDeFinalizar);
    };

    @Override
    @Scheduled(fixedRate = 3000)
    public void sessaoTimeProcessor() {
        for (Entry<Long, LocalTime> sessao: sessoesEmVotacao.entrySet()){
            if(LocalTime.now().isAfter(sessao.getValue())){
                finalizarSessao(sessao.getKey());
                sessoesEmVotacao.remove(sessao.getKey());
            }
        }
    }

    @Override
    public void finalizarSessao(Long sessao_id) {
        Sessao sessaoFinalizar = new Sessao();
        Optional<Sessao> sessao = sessaoRepository.findById(sessao_id);
        sessaoFinalizar = sessao.get();
        sessaoFinalizar.setStatus_sessao(StatusSessaoEnum.FINALIZADA);
        sessaoRepository.save(sessaoFinalizar);
        pautaService.alterarStatus(sessaoFinalizar.getPauta_id(), StatusPautaEnum.FINALIZADA);
        System.out.println("INFO: SESSÃO " + sessao_id + " FINALIZADA!");
    }

}
