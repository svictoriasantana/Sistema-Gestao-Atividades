package br.uesc.gestao_gepa.controller;

import br.uesc.gestao_gepa.model.Tarefa;
import br.uesc.gestao_gepa.model.StatusTarefa;
import br.uesc.gestao_gepa.model.PrioridadeTarefa;
import br.uesc.gestao_gepa.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefa", description = "Gerenciamento de tarefas")
public class TarefaController {
    
    private final TarefaService tarefaService;

    @Operation(summary = "Cadastrar nova tarefa")
    @PostMapping
    public ResponseEntity<Tarefa> cadastrar(@RequestBody Tarefa tarefa) {
        return new ResponseEntity<>(tarefaService.salvarTarefa(tarefa), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar status da tarefa")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Tarefa> atualizarStatus(
            @PathVariable Long id, 
            @RequestParam StatusTarefa status) {
        return ResponseEntity.ok(tarefaService.atualizarStatus(id, status));
    }

    @Operation(summary = "Atualizar detalhes da tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(tarefaService.atualizarTarefa(id, tarefa));
    }

    @Operation(summary = "Deletar tarefa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar tarefas de um bolsista")
    @GetMapping("/bolsista/{bolsistaId}")
    public ResponseEntity<List<Tarefa>> listarDoBolsista(@PathVariable Long bolsistaId) {
        List<Tarefa> tarefas = tarefaService.listasTarefasDoBolsista(bolsistaId);
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Listar tarefas de um projeto")
    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<Tarefa>> listarDoProjeto(@PathVariable Long projetoId) {
        List<Tarefa> tarefas = tarefaService.listarTarefasDoProjeto(projetoId);
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Listar tarefas por status")
    @GetMapping("/status")
    public ResponseEntity<List<Tarefa>> listarPorStatus(@RequestParam StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.listarPorStatus(status);
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Listar tarefas de um bolsista por status")
    @GetMapping("/bolsista/{bolsistaId}/status")
    public ResponseEntity<List<Tarefa>> listarDoBolsistaPorStatus(
            @PathVariable Long bolsistaId, 
            @RequestParam StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.listarDoBolsistaPorStatus(bolsistaId, status);
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Listar tarefas por prioridade")
    @GetMapping("/prioridade")
    public ResponseEntity<List<Tarefa>> listarPorPrioridade(@RequestParam PrioridadeTarefa prioridade) {
        List<Tarefa> tarefas = tarefaService.listarPorPrioridade(prioridade);
        return ResponseEntity.ok(tarefas);
    }
}
