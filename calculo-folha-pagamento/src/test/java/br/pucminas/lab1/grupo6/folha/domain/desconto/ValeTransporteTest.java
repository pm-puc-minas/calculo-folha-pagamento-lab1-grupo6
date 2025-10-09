package br.pucminas.lab1.grupo6.folha.domain.desconto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaRequest;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;

@SpringBootTest
public class ValeTransporteTest {

    @Test
    public void ValeTransporte_Calcular_ReturnSeisPorCento() {
        //Arrange
        Funcionario funcionario = new Funcionario();
        FolhaRequest folhaRequest = new FolhaRequest();
        funcionario.setSalarioBruto(1000.0);
        Double esperado = 3000.0 * 0.06;

        //Act
        ValeTransporte valeTransporte = new ValeTransporte(funcionario, folhaRequest);
        
        //Assert
        assertEquals(esperado, valeTransporte.getValorDescontado());
    }

}
