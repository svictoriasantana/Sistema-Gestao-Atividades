package br.uesc.gestao_gepa.controller;

import br.uesc.gestao_gepa.model.Usuario;
import br.uesc.gestao_gepa.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios") // O endereço principal no navegador
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Gerenciamento de usuários do sistema")
public class UsuarioController {
    private final UsuarioService usuarioService;
    
    @Operation(summary = "Cadastrar um novo usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro de validação (ex: e-mail duplicado)")
    })
    @PostMapping 
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.salvarUsuario(usuario), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar usuário")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, usuario));
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso!")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Listar apenas bolsistas")
    @GetMapping("/bolsistas") 
    public ResponseEntity<List<Usuario>> listarBolsistas() {
        List<Usuario> bolsistas = usuarioService.listarApenasBolsistas();
        return ResponseEntity.ok(bolsistas);
    }

    @Operation(summary = "Buscar usuários por nome")
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(usuarioService.buscarPorNome(nome));
    }
}
