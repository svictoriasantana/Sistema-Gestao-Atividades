package br.uesc.gestao_gepa.service;

import br.uesc.gestao_gepa.model.RegistroAtividade;
import br.uesc.gestao_gepa.repository.RegistroAtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroAtividadeService {
    
    private final RegistroAtividadeRepository registroRepository;

    public RegistroAtividade salvarRegistro(RegistroAtividade registro) {
        return registroRepository.save(registro);
    }

    public RegistroAtividade atualizarRegistro(Long id, RegistroAtividade registroAtualizado) {
        RegistroAtividade existente = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro não encontrado"));
        
        existente.setDescricao(registroAtualizado.getDescricao());
        existente.setHorasDedicadas(registroAtualizado.getHorasDedicadas());
        
        return registroRepository.save(existente);
    }

    public void deletarRegistro(Long id) {
        registroRepository.deleteById(id);
    }

    public List<RegistroAtividade> listarPorTarefa(Long tarefaId) {
        // Retorna o histórico de uma tarefa específica, do mais recente para o mais antigo
        return registroRepository.findByTarefaIdOrderByDataHoraDesc(tarefaId);
    }

    public List<RegistroAtividade> listarPorBolsista(Long usuarioId) {
        // Retorna todos os relatórios feitos pelo bolsista
        return registroRepository.findByUsuarioId(usuarioId);
    }

}
