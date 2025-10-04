package br.pucminas.lab1.grupo6.folha.domain.funcionário;

import java.io.Serializable;
import java.util.UUID;

import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios_tb")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String CPF;
    private String cargo;
    private Double salarioBruto;
    // Private User user
    private Periculosidade periculosidade;
    private GrauInsalubridade insalubridade;
    // Private ArrayList<FolhaDePagamento> historico

    public Funcionario() {
    };

    public Funcionario(UUID id, String nome, String CPF, String cargo, Double salarioBruto,
            Periculosidade periculosidade, GrauInsalubridade insalubridade) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.cargo = cargo;
        this.salarioBruto = salarioBruto;
        this.insalubridade = insalubridade;
        this.periculosidade = periculosidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
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
        return "Funcionario [id=" + id + ", nome=" + nome + ", CPF=" + CPF + ", cargo=" + cargo + ", salarioBruto="
                + salarioBruto + ", getNome()=" + getNome() + ", getCPF()=" + getCPF() + ", getCargo()=" + getCargo()
                + ", getSalarioBruto()=" + getSalarioBruto() + ", getInsalubridade()=" + getInsalubridade() + ", getPericulosidade()=" + getPericulosidade() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
                + ", toString()=" + super.toString() + "]";
    }

}
