package com.example.lab2.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Schema(description = "Representação base de um usuário do sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String nome;

    @Schema(description = "RG do usuário", example = "12.345.678-9")
    private String rg;

    @Schema(description = "CPF do usuário", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Endereço completo do usuário", example = "Rua das Flores, 123")
    private String endereco;

    @Schema(description = "Profissão do usuário", example = "Engenheiro")
    private String profissao;

    @ElementCollection
    @Schema(description = "Lista de entidades empregadoras")
    private List<String> entidadesEmpregadoras;

    @ElementCollection
    @Schema(description = "Lista de rendimentos do usuário", example = "[5000.0, 2000.0]")
    private List<Float> rendimentos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    public List<String> getEntidadesEmpregadoras() { return entidadesEmpregadoras; }
    public void setEntidadesEmpregadoras(List<String> entidadesEmpregadoras) { this.entidadesEmpregadoras = entidadesEmpregadoras; }

    public List<Float> getRendimentos() { return rendimentos; }
    public void setRendimentos(List<Float> rendimentos) { this.rendimentos = rendimentos; }
}