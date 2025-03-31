package org.example.alugueldecarros.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;

    @ElementCollection
    private List<String> entidadesEmpregadoras;

    @ElementCollection
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
