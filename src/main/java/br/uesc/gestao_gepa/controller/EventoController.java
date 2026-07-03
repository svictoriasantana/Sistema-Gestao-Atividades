package br.uesc.gestao_gepa.controller;

import br.uesc.gestao_gepa.model.Evento;
import br.uesc.gestao_gepa.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
@Tag(name = "Eventos", description = "Mural de eventos relacionados à alfabetização")
public class EventoController {
    
    private final EventoService eventoService;

    @Operation(summary = "Cadastrar novo evento")
    @PostMapping
    public ResponseEntity<Evento> cadastrar(@RequestBody Evento evento) {
        return new ResponseEntity<>(eventoService.salvarEvento(evento), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar evento")
    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.atualizarEvento(id, evento));
    }

    @Operation(summary = "Deletar evento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todos os eventos")
    @GetMapping
    public ResponseEntity<List<Evento>> listarMural() {
        List<Evento> eventos = eventoService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    
}
