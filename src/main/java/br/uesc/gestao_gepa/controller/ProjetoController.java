package br.uesc.gestao_gepa.controller;

import br.uesc.gestao_gepa.model.Projeto;
import br.uesc.gestao_gepa.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
@RequiredArgsConstructor
@Tag(name = "Projeto", description = "Gerenciamento de projetos")
public class ProjetoController {
    
    private final ProjetoService projetoService;

    @Operation(summary = "Cadastrar projeto")
    @PostMapping
    public ResponseEntity<Projeto> cadastrar(@RequestBody Projeto projeto) {
        Projeto novoProjeto = projetoService.salvarProjeto(projeto);
        return ResponseEntity.ok(novoProjeto);
    }

    @Operation(summary = "Atualizar projeto")
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @RequestBody Projeto projeto) {
        return ResponseEntity.ok(projetoService.atualizarProjeto(id, projeto));
    }

    @Operation(summary = "Deletar projeto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletarProjeto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todos os projetos")
    @GetMapping
    public ResponseEntity<List<Projeto>> listarTodosProjetos() {
        List<Projeto> projetos = projetoService.listarTodosProjetos();
        return ResponseEntity.ok(projetos);
    }

    @Operation(summary = "Listar projetos de um bolsista")
    @GetMapping("/bolsista/{bolsistaId}")
    public ResponseEntity<List<Projeto>> listarDoBolsista(@PathVariable Long bolsistaId) {
        List<Projeto> projetos = projetoService.listarProjetosDoBolsista(bolsistaId);
        return ResponseEntity.ok(projetos);
    }

    @Operation(summary = "Listar equipe de um projeto")
    @GetMapping("/{projetoId}/equipe")
    public ResponseEntity<List<Object>> listarEquipe(@PathVariable Long projetoId) {
        List<Object> equipe = projetoService.listarEquipeDoProjeto(projetoId);
        return ResponseEntity.ok(equipe);
    }

    @Operation(summary = "Adicionar bolsista a um projeto")
    @PostMapping("/{projetoId}/adicionar-bolsista/{bolsistaId}")
    public ResponseEntity<Projeto> adicionarBolsista(@PathVariable Long projetoId, @PathVariable Long bolsistaId) {
        Projeto projeto = projetoService.adicionarBolsistaAoProjeto(projetoId, bolsistaId);
        try {
            return ResponseEntity.ok(projeto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Retorna 400 se houver algum problema
        }
    }
}
