package br.pucminas.lab1.grupo6.folha.domain.folha;

import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import jakarta.persistence.*;

@Entity //Anotação que indica que a entidade será uma tabela no banco de dados
@Table (name = "FolhaDePagamento") //Anotação que define o nome da tabela no banco de dados
public class FolhaDePagamento{

    @Id //Anotação que define o atributo como Primary Key (PK)
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //Anotação que define que a Primary Key será gerada automaticamente como UUID
    private int idFolha;

    private YearMonth mes;
    private Double valorDeDescontoINSS;
    private Double valorDeDescontoVT;
    private Double valorDeDescontoVA;
    private Double valorDeDescontoFGTS;
    private Double valorDeDescontoIRRF;
    private Double salarioLiquido;
    private int diasTrabalhados;
    private int horasTrabalhadas;

    @ManyToOne (optional = true) //Anotação que aplica cardinalidade N:1 sem obrigatoriedade (optional = true)
    @JoinColumn (name = "funcionario_id") //Anotação que define idFuncionario como Foreign Key (FK)
    @JsonIgnore
    private Funcionario funcionario;

    public FolhaDePagamento(){}

    public FolhaDePagamento (Funcionario funcionario, YearMonth mes, Double valorDeDescontoINSS, Double valorDeDescontoVT, Double valorDeDescontoVA, Double valorDeDescontoFGTS, Double valorDeDescontoIRRF, Double salarioLiquido, int diasTrabalhados, int horasTrabalhadas){
        this.funcionario = funcionario;
        this.mes = mes;
        this.valorDeDescontoINSS = valorDeDescontoINSS;
        this.valorDeDescontoVT = valorDeDescontoVT;
        this.valorDeDescontoVA = valorDeDescontoVA;
        this.valorDeDescontoFGTS = valorDeDescontoFGTS;
        this.valorDeDescontoIRRF = valorDeDescontoIRRF;
        this.salarioLiquido = salarioLiquido;
        this.diasTrabalhados = diasTrabalhados;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public int getIdFolha () { return idFolha; }

    public void setIdFolha(int idFolha) {
        this.idFolha = idFolha;
    }

    public YearMonth getMes() {
        return mes;
    }

    public void setMes(YearMonth mes) {
        this.mes = mes;
    }

    public Double getValorDeDescontoINSS() {
        return valorDeDescontoINSS;
    }

    public void setValorDeDescontoINSS(Double valorDeDescontoINSS) {
        this.valorDeDescontoINSS = valorDeDescontoINSS;
    }

    public Double getValorDeDescontoVT() {
        return valorDeDescontoVT;
    }

    public void setValorDeDescontoVT(Double valorDeDescontoVT) {
        this.valorDeDescontoVT = valorDeDescontoVT;
    }

    public Double getValorDeDescontoVA() {
        return valorDeDescontoVA;
    }

    public void setValorDeDescontoVA(Double valorDeDescontoVA) {
        this.valorDeDescontoVA = valorDeDescontoVA;
    }

    public Double getValorDeDescontoFGTS() {
        return valorDeDescontoFGTS;
    }

    public void setValorDeDescontoFGTS(Double valorDeDescontoFGTS) {
        this.valorDeDescontoFGTS = valorDeDescontoFGTS;
    }

    public Double getValorDeDescontoIRRF() {
        return valorDeDescontoIRRF;
    }

    public void setValorDeDescontoIRRF(Double valorDeDescontoIRRF) {
        this.valorDeDescontoIRRF = valorDeDescontoIRRF;
    }

    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "FolhaDePagamento{" +
                "horasTrabalhadas=" + horasTrabalhadas +
                ", diasTrabalhados=" + diasTrabalhados +
                ", salarioLiquido=" + salarioLiquido +
                ", valorDeDescontoIRRF=" + valorDeDescontoIRRF +
                ", valorDeDescontoFGTS=" + valorDeDescontoFGTS +
                ", valorDeDescontoVA=" + valorDeDescontoVA +
                ", valorDeDescontoVT=" + valorDeDescontoVT +
                ", valorDeDescontoINSS=" + valorDeDescontoINSS +
                ", mes=" + mes +
                ", idFolha=" + idFolha +
                '}';
    }
}