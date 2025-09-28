package br.pucminas.lab1.grupo6.folha.domain.folha;

import java.time.YearMonth;

public class FolhaRequest {

    private YearMonth mes;
    private int diasTrabalhados;
    private Double jornadaMensal;
    private Double jornadaSemanal;
    private Double valeTransporteRecebido;
    private int cargaDiaria;
    private int horasExtra;

    public FolhaRequest(YearMonth mes, int diasTrabalhados, Double jornadaMensal, Double jornadaSemanal,
            Double valeTransporteRecebido, int cargaDiaria, int horasExtra) {
        this.mes = mes;
        this.diasTrabalhados = diasTrabalhados;
        this.jornadaMensal = jornadaMensal;
        this.jornadaSemanal = jornadaSemanal;
        this.valeTransporteRecebido = valeTransporteRecebido;
        this.cargaDiaria = cargaDiaria;
        this.horasExtra = horasExtra;
    }
    
    public YearMonth getMes() {
        return mes;
    }
    public void setMes(YearMonth mes) {
        this.mes = mes;
    }
    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }
    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }
    public Double getJornadaMensal() {
        return jornadaMensal;
    }
    public void setJornadaMensal(Double jornadaMensal) {
        this.jornadaMensal = jornadaMensal;
    }
    public Double getJornadaSemanal() {
        return jornadaSemanal;
    }
    public void setJornadaSemanal(Double jornadaSemanal) {
        this.jornadaSemanal = jornadaSemanal;
    }
    public Double getValeTransporteRecebido() {
        return valeTransporteRecebido;
    }
    public void setValeTransporteRecebido(Double valeTransporteRecebido) {
        this.valeTransporteRecebido = valeTransporteRecebido;
    }
    public int getCargaDiaria() {
        return cargaDiaria;
    }
    public void setCargaDiaria(int cargaDiaria) {
        this.cargaDiaria = cargaDiaria;
    }
    public int getHorasExtra() {
        return horasExtra;
    }
    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    
}
