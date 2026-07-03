package br.uesc.gestao_gepa.repository;

import br.uesc.gestao_gepa.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Listar eventos por data limite mais próxima
    List<Evento> findAllByOrderByDataLimiteAsc();
}
