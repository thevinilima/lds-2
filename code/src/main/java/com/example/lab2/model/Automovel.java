package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@NoArgsConstructor
@Schema(description = "Representação de um automóvel")
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do automóvel", example = "1")
    private Long id;

    @Schema(description = "Matrícula do automóvel", example = "ABC123")
    private String matricula;

    @Schema(description = "Ano de fabricação", example = "2022")
    private Integer ano;

    @Schema(description = "Marca do automóvel", example = "Toyota")
    private String marca;

    @Schema(description = "Modelo do automóvel", example = "Corolla")
    private String modelo;

    @Schema(description = "Placa do automóvel", example = "ABC-1234")
    private String placa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @Schema(description = "Proprietário do automóvel")
    private Cliente proprietario;
}