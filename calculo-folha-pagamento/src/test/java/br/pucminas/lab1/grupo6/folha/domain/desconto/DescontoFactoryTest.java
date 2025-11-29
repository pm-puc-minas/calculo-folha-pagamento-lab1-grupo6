package br.pucminas.lab1.grupo6.folha.domain.desconto;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.dtos.request.FolhaRequest;

class DescontoFactoryTest {

    @Test
    void deveCriarTiposDeDescontoCorretos() {
        var factory = new DescontoFactory();
        var funcionario = new Funcionario();
        funcionario.setSalarioBruto(1000.0);

        var req = new FolhaRequest();
        req.setMes(YearMonth.of(2025, 1));
        req.setDiasTrabalhados(20);
        req.setCargaDiaria(8);
        req.setValorValeAlimentacaoDiario(10.0);
        req.setValeTransporteRecebido(150.0);
        req.setNumeroDeDependentes(0);
        req.setValorPensaoAlimenticia(0.0);

        var va  = factory.criarValeAlimentacao(funcionario, req);
        var vt = factory.criarValeTransporte(funcionario, req);
        var inss = factory.criarInss(funcionario, req);
        var fgts = factory.criarFgts(funcionario, req);
        var irrf = factory.criarIrrf(funcionario, req);

        assertNotNull(va);
        assertNotNull(vt);
        assertNotNull(inss);
        assertNotNull(fgts);
        assertNotNull(irrf);

        assertInstanceOf(ValeAlimentacao.class, va);
        assertInstanceOf(ValeTransporte.class, vt);
        assertInstanceOf(Inss.class, inss);
        assertInstanceOf(Fgts.class, fgts);
        assertInstanceOf(Irrf.class, irrf);
    }
}
