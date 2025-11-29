package br.pucminas.lab1.grupo6.folha.domain.funcionário;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.lab1.grupo6.folha.domain.enums.Cargo;
import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "funcionarios_tb")
public class Funcionario extends User {

    private Double salarioBruto;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    
    @Enumerated(EnumType.STRING)
    private Periculosidade periculosidade;
    
    @Enumerated(EnumType.STRING)
    private GrauInsalubridade insalubridade;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FolhaDePagamento> folhasDePagamento;

    public Funcionario() {
    };

    public Funcionario(UUID id, String nome, String CPF, Cargo cargo, Double salarioBruto,
            Periculosidade periculosidade, GrauInsalubridade insalubridade, String email, String password, Role role) {
        super(id, email, password, role, nome, CPF);
        this.cargo = cargo;
        this.salarioBruto = salarioBruto;
        this.insalubridade = insalubridade;
        this.periculosidade = periculosidade;
        this.folhasDePagamento = new ArrayList<>();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getInsalubridade() {
        return this.insalubridade.getPercentual();
    }

    public void setInsalubridade(GrauInsalubridade insalubridade) {
        this.insalubridade = insalubridade;
    }

    public double getPericulosidade() {
        return this.periculosidade.getPercentual();
    }

    public void setPericulosidade(Periculosidade periculosidade) {
        this.periculosidade = periculosidade;
    }

    @JsonIgnore
    public List<FolhaDePagamento> getFolhasDePagamento() {
        return folhasDePagamento;
    }

    public void adicionarFolhaDePagamento(FolhaDePagamento folha) {
        this.folhasDePagamento.add(folha);
    }
    public void removerFolhaDePagamento(FolhaDePagamento folha) {
        this.folhasDePagamento.remove(folha);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario [id=" + id + ", nome=" + getNome() + ", CPF=" + getCPF() + ", cargo=" + cargo + ", salarioBruto="
                + salarioBruto + "]";
    }
}
