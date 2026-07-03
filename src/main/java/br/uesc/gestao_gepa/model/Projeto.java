package br.uesc.gestao_gepa.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "projeto")
@Data   // Gera getters, setters, equals, hashCode e toString
@NoArgsConstructor 
@AllArgsConstructor

public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(length = 400)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "coordenadora_id", nullable = false)
    private Usuario coordenadora; // Todos os projetos incluem a coordenadora responsável

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
        name = "projeto_bolsistas",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    @JsonIgnoreProperties("projetos") // Impede o loop no sentido inverso
    private Set<Usuario> bolsistas = new HashSet<>();

    // Métodos Auxiliares para garantir a integridade bidirecional
    public void adicionarBolsista(Usuario usuario) {
        this.bolsistas.add(usuario);
        usuario.getProjetos().add(this);
    }

    public void removerBolsista(Usuario usuario) {
        this.bolsistas.remove(usuario);
        usuario.getProjetos().remove(this);
    }
}
