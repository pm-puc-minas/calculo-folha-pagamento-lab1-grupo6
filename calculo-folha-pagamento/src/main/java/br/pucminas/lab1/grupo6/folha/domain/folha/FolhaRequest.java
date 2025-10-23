package br.pucminas.lab1.grupo6.folha.domain.folha;

import java.time.YearMonth;
import java.util.UUID;

public class FolhaRequest {

    private UUID idFuncionario;
    private YearMonth mes;
    private int diasTrabalhados;
    private Double jornadaMensal;
    private Double jornadaSemanal;
    private Double valeTransporteRecebido;
    private int cargaDiaria;
    private int horasExtra;
    private Double valorValeAlimentacaoDiario;
    private int numeroDeDependentes;
    private Double valorPensaoAlimenticia;

    public FolhaRequest() {}

    public FolhaRequest(YearMonth mes, int diasTrabalhados, Double jornadaMensal, Double jornadaSemanal,
            Double valeTransporteRecebido, int cargaDiaria, int horasExtra, Double valorValeAlimentacaoDiario,
            int numeroDeDependentes, Double valorPensaoAlimenticia, UUID idFuncionario) {
        this.mes = mes;
        this.diasTrabalhados = diasTrabalhados;
        this.jornadaMensal = jornadaMensal;
        this.jornadaSemanal = jornadaSemanal;
        this.valeTransporteRecebido = valeTransporteRecebido;
        this.cargaDiaria = cargaDiaria;
        this.horasExtra = horasExtra;
        this.valorValeAlimentacaoDiario = valorValeAlimentacaoDiario;
        this.numeroDeDependentes = numeroDeDependentes;
        this.valorPensaoAlimenticia = valorPensaoAlimenticia;
        this.idFuncionario = idFuncionario;
    }
    
    public YearMonth getMes() {
        return mes;
    }
    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }
    public Double getJornadaMensal() {
        return jornadaMensal;
    }
    public Double getJornadaSemanal() {
        return jornadaSemanal;
    }
    public Double getValeTransporteRecebido() {
        return valeTransporteRecebido;
    }
    public double getCargaDiaria() {
        return cargaDiaria;
    }
    public int getHorasExtra() {
        return horasExtra;
    }
    public Double getValorValeAlimentacaoDiario() {
        return valorValeAlimentacaoDiario;
    }
    public int getNumeroDeDependentes() {
        return numeroDeDependentes;
    }
    public Double getValorPensaoAlimenticia() {
        return valorPensaoAlimenticia;
    }
    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    public void setMes(YearMonth mes) {
        this.mes = mes;
    }
    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }
    public void setJornadaMensal(Double jornadaMensal) {
        this.jornadaMensal = jornadaMensal;
    }
    public void setJornadaSemanal(Double jornadaSemanal) {
        this.jornadaSemanal = jornadaSemanal;
    }
    public void setValeTransporteRecebido(Double valeTransporteRecebido) {
        this.valeTransporteRecebido = valeTransporteRecebido;
    }
    public void setCargaDiaria(int cargaDiaria) {
        this.cargaDiaria = cargaDiaria;
    }
    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }
    public void setValorValeAlimentacaoDiario(Double valorValeAlimentacaoDiario) {
        this.valorValeAlimentacaoDiario = valorValeAlimentacaoDiario;
    }
    public void setNumeroDeDependentes(int numeroDeDependentes) {
        this.numeroDeDependentes = numeroDeDependentes;
    }
    public void setValorPensaoAlimenticia(Double valorPensaoAlimenticia) {
        this.valorPensaoAlimenticia = valorPensaoAlimenticia;
    }
    
}
