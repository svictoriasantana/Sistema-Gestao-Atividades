package br.uesc.gestao_gepa.repository;

import br.uesc.gestao_gepa.model.RegistroAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, Long> {
    // Listar todo o histórico de diários de bordo de uma tarefa específica
    List<RegistroAtividade> findByTarefaIdOrderByDataHoraDesc(Long tarefaId);

    // Listar relatórios feitos por um bolsista específico
    List<RegistroAtividade> findByUsuarioId(Long usuarioId);
}
