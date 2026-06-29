package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDateTime data;
    private Double preco;
    private int capacidadeMaxima;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;

    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    private List<Ingresso> ingressos;
}