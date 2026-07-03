package br.uesc.gestao_gepa.service;

import br.uesc.gestao_gepa.model.Projeto;
import br.uesc.gestao_gepa.model.Usuario;
import br.uesc.gestao_gepa.repository.ProjetoRepository;
import br.uesc.gestao_gepa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;

    private final UsuarioRepository usuarioRepository;

    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto atualizarProjeto(Long id, Projeto projetoAtualizado) {
        Projeto existente = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        
        existente.setTitulo(projetoAtualizado.getTitulo());
        existente.setDescricao(projetoAtualizado.getDescricao());
        
        return projetoRepository.save(existente);
    }

    public void deletarProjeto(Long id) {
        projetoRepository.deleteById(id);
    }

    public List<Projeto> listarTodosProjetos() {
        return projetoRepository.findAllByOrderByTituloAsc();
    }

    public List<Projeto> listarProjetosDoBolsista(Long usuarioId) {
        return projetoRepository.findByBolsistasIdOrderByTituloAsc(usuarioId);
    }

    // Listar a equipe (bolsistas) de um projeto
    public List<Object> listarEquipeDoProjeto(Long projetoId) {
        return projetoRepository.listarBolsistasDoProjeto(projetoId);
    }

    public Projeto adicionarBolsistaAoProjeto(Long projetoId, Long usuarioId) {
        // Busca o projeto no banco
        Projeto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado."));

        // Regra de Negócio: Máximo de 2 bolsistas
        if (projeto.getBolsistas().size() >=2) {
            throw new RuntimeException("Erro: O projeto atingiu o limite de 2 bolsistas.");
        }

        // Busca bolsista
        Usuario bolsista = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Bolsista não encontrado."));

        // Associa bolsista ao projeto
        projeto.adicionarBolsista(bolsista);
        return projetoRepository.save(projeto);
    }
}
