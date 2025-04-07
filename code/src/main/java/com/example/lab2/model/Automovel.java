package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private Integer ano;
    private String marca;
    private String modelo;
    private String placa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false) // Ensure nullable = false
    private Cliente proprietario;
}