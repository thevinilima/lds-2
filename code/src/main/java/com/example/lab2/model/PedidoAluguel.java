package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class PedidoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "automovel_id", nullable = false)
    private Automovel automovel;

    private LocalDate dataInicio;

    private LocalDate dataTermino;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public enum StatusPedido {
        PENDENTE, APROVADO, RECUSADO
    }
}