package br.uesc.gestao_gepa.repository;

import br.uesc.gestao_gepa.model.Tarefa;
import br.uesc.gestao_gepa.model.StatusTarefa;
import br.uesc.gestao_gepa.model.PrioridadeTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Lista tarefas de um bolsista ordenadas pelo prazo mais urgente
    List<Tarefa> findByResponsavelIdOrderByDataPrazoAsc(Long responsavelId);

    // Lista tarefas de um projeto ordenadas por prazo
    List<Tarefa> findByProjetoIdOrderByDataPrazoAsc(Long projetoId);

    // Filtrar tarefas gerais por status e ordenar por prazo
    List<Tarefa> findByStatusOrderByDataPrazoAsc(StatusTarefa status);

    // Filtrar tarefas de um bolsista por status e ordenar por prazo
    List<Tarefa> findByResponsavelIdAndStatusOrderByDataPrazoAsc(Long responsavelId, StatusTarefa status);

    // Filtrar tarefas por prioridade e ordenar por prazo
    List<Tarefa> findByPrioridadeOrderByDataPrazoAsc(PrioridadeTarefa prioridade);
    
}
