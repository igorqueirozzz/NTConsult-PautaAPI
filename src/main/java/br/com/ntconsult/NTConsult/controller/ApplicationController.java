package br.com.ntconsult.NTConsult.controller;

import br.com.ntconsult.NTConsult.DTO.CooperadoDTO;
import br.com.ntconsult.NTConsult.DTO.PautaDTO;
import br.com.ntconsult.NTConsult.DTO.SessaoDTO;
import br.com.ntconsult.NTConsult.domain.model.Pauta;
import br.com.ntconsult.NTConsult.services.PautaService;
import br.com.ntconsult.NTConsult.services.SessaoManagerService;
import br.com.ntconsult.NTConsult.exception.SessaoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/PautaApi")
@Api(value = "API REST Pauta Votação")
public class ApplicationController {

    @Autowired
    PautaService pautaService;
    @Autowired
    SessaoManagerService sessaoManagerService;


    @ApiOperation(value = "Cadastra uma pauta para ser votada.")
    @PostMapping("/cadastrarPauta")
    public ResponseEntity cadastrarPauta(@RequestBody PautaDTO pautaDTO){
        try {
            return pautaService.cadastrarPauta(pautaDTO);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Inicia a sessão de votação de uma pauta previamente definida.")
    @PostMapping("/abrirSessaoVotacao")
    public ResponseEntity abrirSessaoVotacao(@RequestBody SessaoDTO sessaoDTO){
        if (sessaoDTO.getDuracao() > 60 || sessaoDTO.getDuracao() < 1){
            throw new SessaoException("A DURAÇÃO DA VOTAÇÃO DEVERÁ SER DE NO MINIMO 1 MINUTO E NO MAXIMO 60 MINUTOS");
        }
         ResponseEntity responseEntity = sessaoManagerService.abrirSessao(sessaoDTO);
        return responseEntity;
    }

    @ApiOperation(value = "Retorna uma lista com todas as pautas que já foram votadas.")
    @GetMapping("/verPautasFinalizadas")
    public ResponseEntity<List<Pauta>> getAllPautasFinalizadas(){
        List<Pauta> pautas = pautaService.findAllFinalizadas();
        if (!pautas.isEmpty()){
            return ResponseEntity.ok(pautas);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Retorna todas as pautas que ainda não iniciaram a votação.")
    @GetMapping("/verPautasNaoIniciadas")
    public ResponseEntity<List<Pauta>> getAllPautasNaoFinalizadas(){
        List<Pauta> pautas = pautaService.findAllNaoIniciadas();
        if (!pautas.isEmpty()){
            return  ResponseEntity.ok(pautas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Retorna todas as pautas em votação.")
    @GetMapping("/verPautasEmVotacao")
    public ResponseEntity<List<Pauta>> getAllPautasEmVotacao(){
        List<Pauta> pautas = pautaService.findAllEmVotacao();
        if (!pautas.isEmpty()){
            return  ResponseEntity.ok(pautas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Registra um voto em uma pauta que esta em votação.")
    @PostMapping("/votar")
    public ResponseEntity votarEmUmaPauta(@RequestBody CooperadoDTO cooperadoDTO) throws Exception {
        pautaService.votarEmUmaPauta(cooperadoDTO);
        return ResponseEntity.ok("VOTO REALIZADO COM SUCESSO.");
    }

}
