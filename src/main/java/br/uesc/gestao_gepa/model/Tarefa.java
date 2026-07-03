package br.uesc.gestao_gepa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tarefa")
@Data   // Gera getters, setters, equals, hashCode e toString
@NoArgsConstructor 
@AllArgsConstructor

public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 400)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataPrazo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusTarefa status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrioridadeTarefa prioridade;
    
    @Column(length = 500)
    private String link_anexo;
    
    // ------------ Relacionamentos ------------
    @ManyToOne 
    @JoinColumn(name = "responsavel_id", nullable = false)
    @JsonIgnoreProperties({"projetos"})
    private Usuario responsavel; // Varias tarefas para o mesmo Bolsista Responsável

    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false)
    @JsonIgnoreProperties({"projetos"})
    private Usuario criador; // Varias tarefas criadas pela mesma Coordenadora/Bolsista

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    @JsonIgnoreProperties({"bolsistas", "coordenadora"})
    private Projeto projeto; // Várias tarefas pertencem a um Projeto
}
