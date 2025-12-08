# 💼 Sistema de Folha de Pagamento

Projeto desenvolvido para a disciplina **Programação Modular (2025)**, com foco em um sistema de gestão de folha de pagamento para RH, utilizando **Java + Spring Boot** no backend e frontend web.

---

## 👥 Integrantes

- Alice Souza Lima  
- Felipe Paz Carvalho Batista  
- Lorena Aparecida de Paula Pereira  
- Maria Clara Neri Stankunas  
- Vitor de Roma Honório  
- Zaine Mendes Torres

---

## 📑 Sumário da Documentação

- [Aplicações de POO (Herança, Interface, Polimorfismo, Classe Abstrata)](./docs/aplicacoes-poo.md)
- [Arquitetura do Sistema (Visão Geral, Estrutura de Pastas)](./docs/arquitetura.md)
- [Modelagem do Sistema (Cartões CRC, Diagrama de Classes)](./docs/modelagem.md)
- [Prototipo do Projeto](./docs/prototipo.md)
- [Testes Unitários (Planejamento, Implementação e Execução dos Testes)](./docs/testes.md)
- [Implementação de Eventos e Preparação para Integração com Frontend (Sprint 3)](./docs/assets/sprint3/aplicacoes.md)

---

## ⚙️ Funcionalidades Principais

- Autenticação de usuários (login/senha)
- Cálculo de salário hora, adicionais, benefícios e descontos
- Relatório detalhado da folha de pagamento
- Interface web simples e intuitiva
- Testes unitários para validação das regras de negócio

---

## 🧰 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Web**
- **Spring Data JPA**
- **Maven**
- **H2 Database**
- **JUnit 5, **Mockito** e **Spring Test**
- **Frontend Web** (tecnologia à escolha do time)

---

[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=20318317&assignment_repo_type=AssignmentRepo)

---

## Execução (perfil dev)

- Backend: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev` (Windows: `./mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev`)
- Frontend: `cd frontend; npm install; npm run dev`
- Credenciais admin pré-criadas no perfil `dev`: usuário `email@email.com` e senha `123` (papel ADMIN). Há também o usuário `admin@admin.com` com senha `admin`.
- Banco H2 em arquivo (`data/testdb`); ao subir com o perfil `dev` os dados de exemplo são criados automaticamente se o repositório de usuários estiver vazio.
