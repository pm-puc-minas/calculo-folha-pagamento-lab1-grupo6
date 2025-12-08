package br.pucminas.lab1.grupo6.folha.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.security.AuthenticatedUser;
import br.pucminas.lab1.grupo6.folha.service.FolhaDePagamentoService;

@RestController
@RequestMapping(value = "/folha")
public class FolhaDePagamentoController {

    @Autowired
    private FolhaDePagamentoService folhaDePagamentoService;

    @PostMapping("gerar-folha")
    public ResponseEntity<FolhaDePagamento> gerarFolha(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody FolhaRequest folhaRequest) {
        UUID targetId = authenticatedUser.getUserEntity().getId();
        
        boolean isAdmin = authenticatedUser.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin && folhaRequest.getFuncionarioId() != null) {
            targetId = folhaRequest.getFuncionarioId();
        }

        FolhaDePagamento folha = folhaDePagamentoService.gerarFolhaDePagamento(folhaRequest, targetId);
        return ResponseEntity.ok().body(folha);
    }

    @GetMapping("/folhas")
    public ResponseEntity<List<FolhaDePagamento>> getAllFolhasPorFuncionarioAutenticado(@AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        List<FolhaDePagamento> folhas = folhaDePagamentoService.getAllFolhasPorFuncionarioAutenticado(authenticatedUser);
        return ResponseEntity.ok().body(folhas);
    }

    @GetMapping("/folhas/{funcionarioId}")
    public ResponseEntity<List<FolhaDePagamento>> getFolhasPorFuncionarioId(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @PathVariable UUID funcionarioId) {
        boolean isAdmin = authenticatedUser.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            
        if (!isAdmin) {
            return ResponseEntity.status(403).build();
        }

        List<FolhaDePagamento> folhas = folhaDePagamentoService.getFolhasPorFuncionarioId(funcionarioId);
        return ResponseEntity.ok().body(folhas);
    }
}
