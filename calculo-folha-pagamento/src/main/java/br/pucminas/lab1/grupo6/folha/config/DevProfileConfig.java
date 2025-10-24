package br.pucminas.lab1.grupo6.folha.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

@Configuration
@Profile("dev")
public class DevProfileConfig implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Funcionario f1 = new Funcionario(null, "teste", "123", "gerente", 1200.0, Periculosidade.SIM, GrauInsalubridade.MEDIO);
        
        // funcionarioRepository.saveAll(Arrays.asList(f1));
    }

}
