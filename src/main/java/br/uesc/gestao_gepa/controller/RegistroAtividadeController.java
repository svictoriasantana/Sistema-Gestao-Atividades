package br.uesc.gestao_gepa.controller;

import br.uesc.gestao_gepa.model.RegistroAtividade;
import br.uesc.gestao_gepa.service.RegistroAtividadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@RequiredArgsConstructor
@Tag(name = "Registros de Atividades", description = "Diário de bordo e horas dedicadas às tarefas.")
public class RegistroAtividadeController {
    
    private final RegistroAtividadeService registroService;

    @Operation(summary = "Registrar atividade")
    @PostMapping
    public ResponseEntity<RegistroAtividade> registrar(@RequestBody RegistroAtividade registro) {
        return new ResponseEntity<>(registroService.salvarRegistro(registro), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar registro")
    @PutMapping("/{id}")
    public ResponseEntity<RegistroAtividade> atualizar(@PathVariable Long id, @RequestBody RegistroAtividade registro) {
        return ResponseEntity.ok(registroService.atualizarRegistro(id, registro));
    }

    @Operation(summary = "Deletar registro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroService.deletarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar registros por tarefa")
    @GetMapping("/tarefa/{tarefaId}")
    public ResponseEntity<List<RegistroAtividade>> listarPorTarefa(@PathVariable Long tarefaId) {
        return ResponseEntity.ok(registroService.listarPorTarefa(tarefaId));
    }

    @Operation(summary = "Listar registros por bolsista")
    @GetMapping("/bolsista/{bolsistaId}")
    public ResponseEntity<List<RegistroAtividade>> listarPorBolsista(@PathVariable Long bolsistaId) {
        return ResponseEntity.ok(registroService.listarPorBolsista(bolsistaId));
    }
}
