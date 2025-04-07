package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Schema(description = "Representação de um pedido de aluguel de automóvel")
public class PedidoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do pedido", example = "1")
    private Long idPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @Schema(description = "Cliente que solicitou o aluguel")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "automovel_id", nullable = false)
    @Schema(description = "Automóvel a ser alugado")
    private Automovel automovel;

    @Schema(description = "Data de início do aluguel", example = "2023-01-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de término do aluguel", example = "2023-01-15")
    private LocalDate dataTermino;

    @Schema(description = "Valor do aluguel", example = "1500.00")
    private BigDecimal valorAluguel;

    @Schema(description = "Caução depositada", example = "500.00")
    private BigDecimal caucao;

    @Schema(description = "Forma de pagamento", example = "CARTAO_CREDITO")
    private String formaPagamento;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status atual do pedido", example = "PENDENTE",
            allowableValues = {"PENDENTE", "APROVADO", "RECUSADO", "CONCLUIDO", "CANCELADO"})
    private StatusPedido status;

    public enum StatusPedido {
        PENDENTE, APROVADO, RECUSADO, CONCLUIDO, CANCELADO
    }
}