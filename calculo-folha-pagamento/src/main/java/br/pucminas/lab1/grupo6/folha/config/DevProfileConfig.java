package br.pucminas.lab1.grupo6.folha.config;

import java.time.YearMonth;
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
import br.pucminas.lab1.grupo6.folha.domain.folha.FolhaDePagamento;
import br.pucminas.lab1.grupo6.folha.domain.funcionário.Funcionario;
import br.pucminas.lab1.grupo6.folha.domain.user.User;
import br.pucminas.lab1.grupo6.folha.repositories.FolhaDePagamentoRepository;
import br.pucminas.lab1.grupo6.folha.repositories.FuncionarioRepository;
import br.pucminas.lab1.grupo6.folha.repositories.UserRepository;

@Configuration
@Profile("dev")
@SuppressWarnings("null")
public class DevProfileConfig implements CommandLineRunner {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FolhaDePagamentoRepository folhaDePagamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        
        Funcionario f1 = new Funcionario(null, "nome", "123", Cargo.ANALISTA, 3000.0, Periculosidade.SIM, GrauInsalubridade.MEDIO, "email@email.com", passwordEncoder.encode("123"), Role.ADMIN);
        funcionarioRepository.saveAll(Arrays.asList(f1));

        FolhaDePagamento folha1 = new FolhaDePagamento(f1, YearMonth.of(2024, 5), 300.0, 150.0, 200.0, 240.0, 100.0, 2200.0, 22, 160);
        FolhaDePagamento folha2 = new FolhaDePagamento(f1, YearMonth.of(2024, 6), 300.0, 150.0, 200.0, 240.0, 100.0, 2200.0, 22, 160);
        FolhaDePagamento folha3 = new FolhaDePagamento(f1, YearMonth.of(2024, 7), 300.0, 150.0, 200.0, 240.0, 100.0, 2200.0, 22, 160);
        FolhaDePagamento folha4 = new FolhaDePagamento(f1, YearMonth.of(2024, 8), 300.0, 150.0, 200.0, 240.0, 100.0, 2200.0, 22, 160);
        folhaDePagamentoRepository.saveAll(Arrays.asList(folha1, folha2, folha3, folha4));

        User admin = new User(null, "admin@admin.com", passwordEncoder.encode("admin"), Role.ADMIN, "admin", "12345");
        userRepository.save(admin);
    }

}
