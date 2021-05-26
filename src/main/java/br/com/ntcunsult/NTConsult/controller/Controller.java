package br.com.ntcunsult.NTConsult.controller;

import br.com.ntcunsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.DTO.SessaoDTO;
import br.com.ntcunsult.NTConsult.domain.model.Pauta;
import br.com.ntcunsult.NTConsult.exception.SessaoException;
import br.com.ntcunsult.NTConsult.services.PautaService;
import br.com.ntcunsult.NTConsult.services.SessaoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class Controller {

    @Autowired
    PautaService pautaService;
    @Autowired
    SessaoManagerService sessaoManagerService;

    @GetMapping("/home")
    public String home(){
        return "/CadastrarPauta: Cadastre uma nova Pauta.\n\n\n" +
                "/AbrirSessao: Abra a sessão de votação de uma pauta já criada.\n" +
                "/VerPautasFinalizadas: veja todas as pautas finalizada e seu resultado.\n" +
                "/VerPautasCriadas: Veja todas as pautas criadas.\n" +
                "/VerPautasEmVotacao: Veja todas as pautas em votação\n" +
                "/Votar: Vote em uma pauta";
    }

    @PostMapping("/CadastrarPauta")
    public ResponseEntity cadastrarPauta(@RequestBody PautaDTO pautaDTO){
        try {
            return pautaService.cadastrarPauta(pautaDTO);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/AbrirSessaoDeVotacao")
    public ResponseEntity abrirSessaoDeVotacao(@RequestBody SessaoDTO sessaoDTO){
        if (sessaoDTO.getDuracao() > 60 || sessaoDTO.getDuracao() < 1){
            throw new SessaoException("A DURAÇÃO DA VOTAÇÃO DEVERÁ SER DE NO MINIMO 1 MINUTO E NO MAXIMO 60 MINUTOS");
        }
        return sessaoManagerService.abrirSessao(sessaoDTO);
    }

    @GetMapping("/VerPautasFinalizadas")
    public ResponseEntity<List<Pauta>> getAllPautasFinalizadas(){
        List<Pauta> pautas = pautaService.findAllFinalizadas();
        if (!pautas.isEmpty()){
            return ResponseEntity.ok(pautas);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/VerPautasNaoFinalizadas")
    public ResponseEntity<List<Pauta>> getAllPautasNaoFinalizadas(){
        List<Pauta> pautas = pautaService.findAllNaoFinalizadas();
        if (!pautas.isEmpty()){
            return  ResponseEntity.ok(pautas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/VerPautasEmVotacao")
    public ResponseEntity<List<Pauta>> getAllPautasEmVotacao(){
        List<Pauta> pautas = pautaService.findAllEmVotacao();
        if (!pautas.isEmpty()){
            return  ResponseEntity.ok(pautas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Votar")
    public ResponseEntity votarEmUmaPauta(@RequestBody CooperadoDTO cooperadoDTO){

        return null;
    }

}
