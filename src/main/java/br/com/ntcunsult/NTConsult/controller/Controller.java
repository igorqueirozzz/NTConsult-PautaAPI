package br.com.ntcunsult.NTConsult.controller;

import br.com.ntcunsult.NTConsult.DTO.PautaDTO;
import br.com.ntcunsult.NTConsult.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class Controller {

    @Autowired
    PautaService pautaService;

    @GetMapping("/")
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

}
