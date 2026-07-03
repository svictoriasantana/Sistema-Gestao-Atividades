package br.uesc.gestao_gepa.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "registro_atividade")
@Data   // Gera getters, setters, equals, hashCode e toString
@NoArgsConstructor  // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos

public class RegistroAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 400)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer horasDedicadas;

    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    @JsonIgnoreProperties({"responsavel", "criador", "projeto"})
    private Tarefa tarefa; // Vários registros de atividade pertencem a uma Tarefa

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"projetos"})
    private Usuario usuario; // Vários registros de atividade podem ser feitos por um mesmo usuário
}
