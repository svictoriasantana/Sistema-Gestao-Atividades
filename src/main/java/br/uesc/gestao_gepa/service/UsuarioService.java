package br.uesc.gestao_gepa.service;

import br.uesc.gestao_gepa.model.Usuario;
import br.uesc.gestao_gepa.model.PerfilUsuario;
import br.uesc.gestao_gepa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        // Evitar e-mails duplicados
        Optional <Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        // Se o usuário existir e o ID for diferente (ou seja, é um cadastro novo tentando usar um email antigo)
        if (usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(usuario.getId())) {
            throw new RuntimeException("Este e-mail já está cadastrado no sistema.");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        existente.setNome(usuarioAtualizado.getNome());
        existente.setPerfil(usuarioAtualizado.getPerfil());
        // A senha e o email não são atualizados nessa mesma rota por segurança
        
        return usuarioRepository.save(existente);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarApenasBolsistas() {
        return usuarioRepository.findByPerfilOrderByNomeAsc(PerfilUsuario.BOLSISTA);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
    }
}