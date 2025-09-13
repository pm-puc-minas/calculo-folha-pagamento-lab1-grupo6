# Requisitos do Projeto

## Visão Geral
Implementar um **sistema de gestão de folha de pagamento** para um software de RH, seguindo **POO, SOLID, modularidade, usabilidade** e **qualidade** com **testes unitários**. O sistema **deve ter autenticação** (login/senha).  
Tecnologia: **Java + Spring Boot** (backend), **frontend web** consumindo endpoints.

---

## Sprint 1 – Análise e Modelagem
- Análise do estudo de caso e domínio
- Identificação de requisitos
- **Cartões CRC**
- **Diagrama de Classes**
- **Prototipação** inicial do frontend (telas)
- Plano de **testes unitários** (apenas planejar)
- **Esqueleto** do projeto Spring Boot

**Entregáveis**: CRC, diagrama, esqueleto no GitHub, UI inicial, plano de testes.

## Sprint 2 – Herança, Interfaces e Testes
- Implementar **testes unitários** da sprint 1
- Aplicar conceitos de reuso com **Herança**
- Especialização e separação de responsabilidades com **Interfaces** e **Classes Abstratas**

**Entregáveis**: código atualizado, relatório, apresentação dos testes.

## Sprint 3 – Polimorfismo Paramétrico, Coleções/Streams, Persistência e Eventos
- Implementação de Polimorfismo Paramétrico (Generics) no sistema
- **Collections** (List/Set/Map) para manipulação dos dados da folha de pagamento
- Emprego de Streams para processamento e filtragem de dados
- **Persistência** em BD relacional
- Implementação de **Eventos** (ex.: cadastro dispara log; geração de folha dispara notificação)
- Preparar integração com frontend
- **Testes unitários** para as **novas funcionalidades**

**Entregáveis**: código atualizado, relatório, apresentação mostrando uso de eventos.

## Sprint 4 – Frontend, Integração e Padrões de Projeto
- Implementação de um **Frontend web** simples com tecnologia à escolha do grupo
- Consumo de **endpoints REST** para operações do sistema (cadastro de funcionários, cálculo da folha, listagem de pagamentos, etc.).
- **Padrões de projeto** (Factory, Singleton, Strategy) no backend
- Integração completa **Front + Back**

**Entregáveis**: sistema completo (Front + Back + Testes unitários), código no GitHub, apresentação final com demonstração funcional.

---

## Características do Produto (alto nível)
- **Cálculo de Jornada de Trabalho** (salário/hora a partir do salário bruto)
- **Adicionais** (periculosidade, insalubridade)
- **Benefícios** (vale transporte, vale alimentação)
- **Descontos** (INSS, FGTS, IRRF)
- **Salário Líquido** (bruto − descontos)
- **Relatório da Folha** (detalhado em tela)

---

## Requisitos Funcionais (RF)
- **RF1** – Calcular **Salário Hora**
- **RF2** – Calcular **Periculosidade** (30% sobre salário, conforme aplicável)
- **RF3** – Calcular **Insalubridade** (10%, 20% ou 40% sobre salário mínimo)
- **RF4** – Calcular **Vale Transporte** (desconto até 6% do salário bruto; ou o valor entregue, o que for menor)
- **RF5** – Calcular **Vale Alimentação** (dias úteis × valor diário; benefício gratuito no escopo)
- **RF6** – Calcular **INSS** (tabela progressiva, 4 faixas)
- **RF7** – Calcular **FGTS** (8% do salário bruto)
- **RF8** – Calcular **IRRF** (tabela progressiva, considerar INSS e dependentes)
- **RF9** – Calcular **Salário Líquido**
- **RF10** – **Exibir Relatório** completo da folha (dados do funcionário, bases, proventos, descontos, bruto, líquido, salário/hora)

---

## Requisitos Não Funcionais (RNF)
- **Usabilidade**: interface simples; mensagens claras e bem formatadas.
- **Manutenibilidade**:
  - POO; **baixo acoplamento, alta coesão**.
  - Código **limpo**, organizado, estruturado, seguindos as **boas práticas** da programação estruturada.
  - Código seguindo **Convenções de código Java.**

---

## Requisitos Extras
- Interface **CLI retrô** (desenho de caixas estilo MS-DOS).
- Uso de **`BigDecimal`** para os cálculos.
- Cálculo automático de **semanas do mês** corrente.