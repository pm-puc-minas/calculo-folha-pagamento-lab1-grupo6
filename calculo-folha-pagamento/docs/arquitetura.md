# 🏗️ Arquitetura do Sistema

## 🌐 Visão Geral
A aplicação adota uma **arquitetura em camadas**. 

Cada camada é responsável por uma parte bem definida da aplicação:

| Camada | Função Principal | Localização |
|--------|------------------|-------------|
| **Configuração (Config)** | Define parâmetros de inicialização e perfis de execução |
| **Controle (Controller)** | Recebe e responde às requisições HTTP |
| **Serviço (Service)** | Atua como camada de orquestração, coordenando o fluxo entre as classes de domínio, fábricas de desconto e repositórios |
| **Domínio (Domain)** | Define as entidades, enums, objetos de transferência, cálculos de descontos. Responsáveis por encapsular a lógica da folha de pagamento |
| **Repositório (Repository)** | Interage com o banco de dados via JPA |
| **Testes (Test)** | Validação de regras, entidades e serviços |

---

## 🗂️ Estrutura de Pastas
- `calculo-folha-pagamento/` — Projeto principal  
  - `pom.xml` — Gerenciador de dependências Maven  
  - `README.md` — Documentação principal do repositório  
  - `src/main/java/` — Código-fonte principal  
    - `br/pucminas/lab1/grupo6/folha/` — Pacote raiz do projeto  
      - `config/` — Configurações globais e perfis da aplicação  
      - `controllers/` — Camada de controle (rotas REST e endpoints HTTP)  
      - `domain/` — Núcleo do domínio e regras de negócio  
        - `desconto/` — Implementações de descontos (INSS, IRRF, FGTS, etc.)  
        - `enums/` — Enumerações de apoio (Roles, Periculosidade, Insalubridade)  
        - `folha/` — Entidades e DTOs de folha de pagamento  
        - `funcionario/` — Entidade principal de funcionário  
        - `user/` — Entidade de autenticação e perfis de usuário  
      - `repositories/` — Interfaces de persistência (Spring Data JPA)  
      - `service/` — Camada de orquestração
      - `FolhaDePagamentoApplication.java` — Classe principal (entry point do Spring Boot)  
  - `src/main/resources/` — Configurações e recursos estáticos   
  - `src/test/java/` — Testes automatizados    
  - `docs/` — Documentação, requisitos, cartões CRC, protótipos  
    - `arquitetura.md` — Estrutura técnica e camadas da aplicação  
    - `requisitos.md` — Requisitos funcionais e não funcionais  
    - `cartoes-crc.md` — Cartões CRC (Classes, Responsabilidades e Colaborações)  
    - `planejamento-testes.md` — Estratégia e plano de testes  
    - `prototipos.md` — Protótipos de interface (Figma)  
    - `assets/modelagem/` — Diagramas e cartões CRC  
    - `assets/prototipo/` — Screenshots dos protótipos de UI

---

