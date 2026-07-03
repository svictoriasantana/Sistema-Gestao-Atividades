package br.uesc.gestao_gepa.service;

import br.uesc.gestao_gepa.model.Evento;
import br.uesc.gestao_gepa.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {
    
    private final EventoRepository eventoRepository;

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento existente = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        existente.setNome(eventoAtualizado.getNome());
        existente.setTipo(eventoAtualizado.getTipo());
        existente.setDataLimite(eventoAtualizado.getDataLimite());
        existente.setLink(eventoAtualizado.getLink());
        
        return eventoRepository.save(existente);
    }

    public void deletarEvento(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAllByOrderByDataLimiteAsc();
    }
}
