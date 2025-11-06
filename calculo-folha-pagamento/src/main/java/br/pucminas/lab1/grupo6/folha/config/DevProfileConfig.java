package br.pucminas.lab1.grupo6.folha.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.pucminas.lab1.grupo6.folha.domain.enums.Cargo;
import br.pucminas.lab1.grupo6.folha.domain.enums.GrauInsalubridade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Periculosidade;
import br.pucminas.lab1.grupo6.folha.domain.enums.Role;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;

@Configuration
@Profile("dev")
public class DevProfileConfig implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        Funcionario f1 = new Funcionario(null, "nome", "123", Cargo.ANALISTA, 3000.0, Periculosidade.SIM, GrauInsalubridade.MEDIO, "email@email.com", passwordEncoder.encode("123"), Role.ADMIN);
        funcionarioRepository.saveAll(Arrays.asList(f1));
    }

}
