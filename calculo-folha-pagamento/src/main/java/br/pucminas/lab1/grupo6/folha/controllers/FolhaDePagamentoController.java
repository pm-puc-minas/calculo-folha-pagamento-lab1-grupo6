package br.pucminas.lab1.grupo6.folha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;
import br.pucminas.lab1.grupo6.folha.service.FolhaDePagamentoService;

@RestController
@RequestMapping(value = "/folha")
public class FolhaDePagamentoController {

    @Autowired
    private FolhaDePagamentoService folhaDePagamentoService;

    @PostMapping("gerar-folha")
    public ResponseEntity<FolhaDePagamento> gerarFolha(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody FolhaRequest folhaRequest) {
        FolhaDePagamento folha = folhaDePagamentoService.gerarFolhaDePagamento(folhaRequest, authenticatedUser);
        return ResponseEntity.ok().body(folha);
    }
}
