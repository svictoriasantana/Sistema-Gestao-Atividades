package br.uesc.gestao_gepa.service;

import br.uesc.gestao_gepa.model.PrioridadeTarefa;
import br.uesc.gestao_gepa.model.StatusTarefa;
import br.uesc.gestao_gepa.model.Tarefa;
import br.uesc.gestao_gepa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    
    private final TarefaRepository tarefaRepository;

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarStatus(Long id, StatusTarefa novoStatus) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setStatus(novoStatus);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        Tarefa existente = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        
        existente.setTitulo(tarefaAtualizada.getTitulo());
        existente.setDescricao(tarefaAtualizada.getDescricao());
        existente.setDataPrazo(tarefaAtualizada.getDataPrazo());
        existente.setPrioridade(tarefaAtualizada.getPrioridade());
        
        return tarefaRepository.save(existente);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

    public List<Tarefa> listasTarefasDoBolsista(Long responsavelId) {
        return tarefaRepository.findByResponsavelIdOrderByDataPrazoAsc(responsavelId);
    }

    public List<Tarefa> listarTarefasDoProjeto(Long projetoId) {
        return tarefaRepository.findByProjetoIdOrderByDataPrazoAsc(projetoId);
    }

    // Filtrar tarefas gerais da coordenadora por status
    public List<Tarefa> listarPorStatus(StatusTarefa status) {
        return tarefaRepository.findByStatusOrderByDataPrazoAsc(status);
    }

    // Filtrar tarefas de um bolsista por status
    public List<Tarefa> listarDoBolsistaPorStatus(Long bolsistaId, StatusTarefa status) {
        return tarefaRepository.findByResponsavelIdAndStatusOrderByDataPrazoAsc(bolsistaId, status);
    }

    // Filtrar por prioridade
    public List<Tarefa> listarPorPrioridade(PrioridadeTarefa prioridade) {
        return tarefaRepository.findByPrioridadeOrderByDataPrazoAsc(prioridade);
    }
}
