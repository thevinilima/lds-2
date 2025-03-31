package org.example.alugueldecarros.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("Cliente")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cliente extends Usuario {

    @ElementCollection
    @CollectionTable(name = "entidades_empregadoras", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "entidade_empregadora")
    private List<String> entidadesEmpregadoras;

    // Outros campos e métodos específicos de Cliente, se necessário
}
