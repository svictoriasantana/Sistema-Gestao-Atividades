package br.uesc.gestao_gepa.repository;

import br.uesc.gestao_gepa.model.Usuario;
import br.uesc.gestao_gepa.model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar um usuário pelo email
    Optional<Usuario> findByEmail(String email);
    
    // Listar bolsistas por ordem alfabética
    List<Usuario> findByPerfilOrderByNomeAsc(PerfilUsuario perfil);

    // Buscar usuários por nome ordenando em ordem alfabética
    List<Usuario> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    
}
