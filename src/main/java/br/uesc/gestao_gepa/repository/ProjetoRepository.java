package br.uesc.gestao_gepa.repository;

import br.uesc.gestao_gepa.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    
    // Lista todos os projetos do sistema em ordem alfabética
    List<Projeto> findAllByOrderByTituloAsc();
    
    // Lista todos os projetos de um bolsista em ordem alfabética
    List<Projeto> findByBolsistasIdOrderByTituloAsc(Long usuarioId);

    // Listar os bolsistas de um projeto específico
    @Query("SELECT p.bolsistas FROM Projeto p WHERE p.id = :projetoId")
    List<Object> listarBolsistasDoProjeto(@Param("projetoId") Long projetoId);
}
