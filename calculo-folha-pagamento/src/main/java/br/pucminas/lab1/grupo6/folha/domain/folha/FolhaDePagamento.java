package br.pucminas.lab1.grupo6.folha.domain.folha;

import java.time.YearMonth;

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
}