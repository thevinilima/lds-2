package com.example.lab2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("Cliente")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "Representação de um cliente")
public class Cliente extends Usuario {

    @ElementCollection
    @CollectionTable(name = "entidades_empregadoras", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "entidade_empregadora")
    @Schema(description = "Lista de entidades empregadoras do cliente", example = "[\"Empresa A\", \"Empresa B\"]")
    private List<String> entidadesEmpregadoras;
}