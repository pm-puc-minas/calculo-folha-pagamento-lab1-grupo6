# 🧩 Modelagem do Sistema

## 🧩 Diagrama de Classes UML
![Diagrama de Classes UML](assets/modelagem/diagrama-classes.png)

---

## 🧾 Cartões CRC

### Funcionario

| **Classe** | `Funcionario` |
|-------------|----------------|
| **Responsabilidades** | - Armazenar dados cadastrais e salariais do colaborador.<br>- Informar salário base, periculosidade e insalubridade.<br>- Calcular salário bruto considerando adicionais.<br>- Manter histórico de folhas de pagamento. |
| **Colaborações** | - `FolhaDePagamento`<br>- `User`<br>- `SalarioService`<br>- `FolhaDePagamentoService`|

### FolhaDePagamento

| **Classe** | `FolhaDePagamento` |
|-------------|--------------------|
| **Responsabilidades** | - Representar a folha mensal de um funcionário.<br>- Consolidar valores de descontos e benefícios (INSS, FGTS, VT, VA, IRRF).<br>- Calcular e armazenar o salário líquido.<br>- Registrar metadados do período (mês, dias e horas trabalhadas). |
| **Colaborações** | - `Funcionario`<br>- `SalarioService`<br>- `FolhaDePagamentoService`|

### User

| **Classe** | `User` |
|-------------|--------|
| **Responsabilidades** | - Representar credenciais do sistema (`email`, `password`).<br>- Controlar permissões de acesso com base em `Role`.<br>- Associar autenticação a um `Funcionario`. |
| **Colaborações** | - `Funcionario`<br>- `Role`|

### FolhaRequest

| **Classe** | `FolhaRequest` |
|-------------|----------------|
| **Responsabilidades** | - Transportar parâmetros de cálculo do período (mês, dias, jornadas, horas extras).<br>- Padronizar a entrada para os serviços de cálculo, evitando parâmetros soltos.<br>- Representar a solicitação de geração da folha. |
| **Colaborações** | - `SalarioService`<br>- `FolhaDePagamentoService`|

### SalarioService

| **Classe** | `SalarioService` |
|-------------|-----------------|
| **Responsabilidades** | - Calcular o salário por hora e adicionais (insalubridade, periculosidade).<br>- Calcular salário líquido com base em proventos e descontos consolidados.<br>- Fornecer valores de base para a geração da folha. |
| **Colaborações** | - `Funcionario`<br>- `FolhaRequest`<br>- `FolhaDePagamentoService`|

### FolhaDePagamentoService

| **Classe** | `FolhaDePagamentoService` |
|-------------|---------------------------|
| **Responsabilidades** | - Orquestrar o processo de geração da folha de pagamento.<br>- Integrar cálculos de descontos e benefícios via `DescontoFactory`.<br>- Consolidar valores finais e instanciar `FolhaDePagamento`.<br>- Centralizar a lógica de fluxo entre serviços e entidades. |
| **Colaborações** | - `Funcionario`<br>- `FolhaRequest`<br>- `DescontoFactory`<br>- `SalarioService`<br>- `ICalcular`|

### DescontoFactory

| **Classe** | `DescontoFactory` |
|-------------|-------------------|
| **Responsabilidades** | - Criação de descontos.<br>- Centralizar a lógica de instanciação de descontos concretos.<br>- Facilitar a manutenção e expansão de novos tipos de desconto. |
| **Colaborações** | - `Desconto` (abstrata)<br>- `FolhaDePagamentoService`<br>- Subclasses de desconto (`Inss`, `Irrf`, `Fgts`, `ValeTransporte`, `ValeAlimentacao`). |

### Desconto

| **Classe** | `Desconto` |
|-------------|------------|
| **Responsabilidades** | - Definir o contrato e a estrutura base para descontos da folha.<br>- Calcular o valor descontado conforme o salário e tipo de benefício.<br>- Ser estendida por classes concretas com regras específicas. |
| **Colaborações** | - `Funcionario`<br>- `FolhaRequest`<br>- `ICalcular`|

### ICalcular

| **Classe** | `ICalcular` |
|-------------|-------------|
| **Responsabilidades** | - Definir o contrato comum para todos os cálculos de desconto ou benefício.<br>- Garantir que todas as subclasses implementem `calcular()`.<br>- Promover padronização entre as implementações concretas. |
| **Colaborações** | - `Desconto`<br>- `FolhaDePagamentoService`|

### Descontos Concretos

| **Classe** | `Inss` / `Irrf` / `Fgts` / `ValeTransporte` / `ValeAlimentacao` |
|-------------|-----------------------------------------------------------------|
| **Responsabilidades** | - Implementar o cálculo de um tipo específico de desconto ou benefício.<br>- Aplicar regras legais ou empresariais (percentuais, limites e faixas).<br>- Retornar o valor a ser descontado ou adicionado. |
| **Colaborações** | - `DescontoFactory`<br>- `Funcionario`<br>- `FolhaRequest`<br>- `ICalcular`|