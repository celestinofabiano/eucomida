# euComida - Backend

## 📌 Visão Geral

O `euComida` é um backend projetado para suportar um marketplace de delivery de comida. O sistema foi desenvolvido com foco em escalabilidade, segurança e boas práticas de desenvolvimento. Esta documentação detalha a arquitetura atual, tecnologias utilizadas e estratégias adotadas para autenticação e segurança.

---

## 🏗 Arquitetura do Projeto

### 🔹 Tecnologias e Frameworks Utilizados

- **Linguagem**: Java 21
- **Framework Principal**: Spring Boot 3.4.3
- **Persistência**: Spring Data JPA + Hibernate
- **Banco de Dados**: PostgreSQL 16
- **Autenticação e Segurança**: OAuth2 (Google), JWT, Spring Security
- **Testes**: JUnit 5, AssertJ, Mockito
- **Documentação da API**: OpenAPI (Swagger)
- **Gerenciamento de Dependências**: Maven
- **Migrações de Banco**: Flyway
- **Containerização**: Docker + Docker Compose
- **Gerenciamento de Conexões**: HikariCP

---

## 🛢 Estrutura do Banco de Dados

O sistema utiliza um banco **relacional (PostgreSQL)** com um modelo normalizado para melhor integridade dos dados.

### 📊 Principais Tabelas:

1. **Usuários (********`users`********\*\*\*\*)**

   - `id` (UUID) - Identificador único
   - `name` (String) - Nome do usuário
   - `email` (String) - E-mail (único)
   - `created_at` (TIMESTAMP) - Data de criação
   - `updated_at` (TIMESTAMP) - Última atualização

2. **Entregadores (********`couriers`********\*\*\*\*)**

   - `id` (UUID) - Identificador único
   - `user_id` (UUID) - Relacionamento com a tabela `users`, único e obrigatório
   - `vehicle_type` (VARCHAR) - Tipo de veículo (`BICYCLE`, `CAR`, `MOTORCYCLE`)
   - `plate_number` (VARCHAR) - Placa do veículo (opcional)
   - `created_at` (TIMESTAMP) - Data de criação
   - `updated_at` (TIMESTAMP) - Última atualização

3. **Pedidos (********`orders`********\*\*\*\*)**

   - `id` (UUID) - Identificador único
   - `user_id` (UUID) - Relacionamento com a tabela `users`
   - `courier_id` (UUID) - Relacionamento com `couriers`
   - `status` (VARCHAR) - Status (`PENDING`, `IN_PROGRESS`, `DELIVERED`, `CANCELED`)
   - `total_price` (DECIMAL) - Valor total do pedido
   - `payment_status` (VARCHAR) - Status do pagamento (`PENDING`, `PAID`, `FAILED`)
   - `created_at` (TIMESTAMP) - Data de criação
   - `updated_at` (TIMESTAMP) - Última atualização

O controle de versões do banco de dados é gerenciado pelo **Flyway**.

---

## 🔑 Estratégia de Autenticação e Autorização

A segurança do sistema é baseada em **OAuth2 e JWT**:

- O backend delega a autenticação ao Google via OAuth2.
- O sistema gera **tokens JWT** para sessões autenticadas.
- O **Spring Security** gerencia  a autenticação dos usuários.
- Os tokens possuem tempo de expiração de 1 hora.

---

## 🚀 Estratégia de Escalabilidade e Segurança da API

### 🔹 Escalabilidade:

- O sistema pode ser escalado horizontalmente ao adicionar novas instâncias da aplicação, permitindo distribuição de carga entre múltiplos servidores.
- O sistema foi estruturado para permitir evolução para microserviços no futuro.
- Uso de **HikariCP** para otimização de conexões ao banco.

### 🔹 Segurança:

- **Spring Security** implementado para garantir a segurança da API, fornecendo autenticação e proteção contra acessos não autorizados.
- **Gerenciamento seguro de credenciais**: As variáveis sensíveis (secrets) são armazenadas de forma segura no ambiente de execução, protegendo informações sensíveis.
- **Autenticação via OAuth2 e JWT** para garantir sessões seguras e evitar acessos não autorizados.
- O sistema utiliza **OAuth2 com Google**, e senhas não são armazenadas no banco de dados para usuários autenticados.

---

## 📖 Documentação da API

A documentação interativa da API pode ser acessada via **Swagger UI** no seguinte link:

🔗 **[Swagger UI - Documentação da API](https://eucomida-k37rx7zl3q-uc.a.run.app/swagger-ui/index.html)**

## 🛠 Como Rodar o Projeto em ambiente LOCAL

### 🔹 Pré-requisitos:

1. **Docker e Docker Compose** instalados (versões mais recentes).
2. **Java 21 e Maven** instalados.

### 🔹 Passos para rodar:

```sh
# Clonar o repositório
git clone https://github.com/seu-repositorio/eucomida.git
cd eucomida

# Construir o projeto
mvn clean install

# Subir os containers (PostgreSQL + API)
docker compose up

# A API estará rodando em http://localhost:8080
```

### 🔹 Executar testes:

```sh
mvn test
```

## 📌 Decisões Técnicas Tomadas

### 1️⃣ Arquitetura e Design do Sistema
- Arquitetura baseada em serviços desacoplados para permitir escalabilidade futura.
- Modelo relacional com PostgreSQL, garantindo integridade dos dados e suporte a transações ACID.
- Uso de Spring Boot para acelerar o desenvolvimento, manter padronização e facilitar integração com outras tecnologias.

### 2️⃣ Escolha das Tecnologias
- Java 21: Escolhido por sua estabilidade, suporte a recursos modernos e melhorias de desempenho.
- Spring Boot 3.4.3: Framework principal pela sua robustez, modularidade e facilidade de configuração.
- Spring Data JPA + Hibernate: Simplifica a persistência e abstrai interações com o banco de dados.
- OAuth2 + JWT: Implementação de autenticação moderna e segura.
- HikariCP: Para otimização de conexões ao banco, melhorando performance.
- Flyway: Controle de versões do banco de dados para manter consistência entre ambientes.
- OpenAPI (Swagger): Documentação interativa da API.
- JUnit 5, AssertJ e Mockito: Stack de testes robusta, garantindo qualidade do código.

### 3️⃣ Autenticação e Segurança
- OAuth2 com Google: Delegação de autenticação para um provedor externo confiável.
- JWT para autorização: Sessões autenticadas e seguras, reduzindo carga no banco.
- Spring Security: Controle de acesso granular e proteção contra ataques comuns (XSS, CSRF, etc.).
- Variáveis sensíveis protegidas: Uso de variáveis de ambiente para segredos e chaves.

### 4️⃣ Escalabilidade e Performance
- Arquitetura escalável horizontalmente: Permite aumentar o número de instâncias sem impacto no sistema.
- Gerenciamento eficiente de conexões: HikariCP otimiza o uso do banco, evitando gargalos.
- Possível evolução para microserviços: O sistema foi estruturado para permitir modularização no futuro.

### 5️⃣ Containerização e Facilidade de Deploy
- Uso de Docker e Docker Compose: Facilita configuração e execução do ambiente, garantindo portabilidade.
- Banco PostgreSQL rodando em container: Garantia de um ambiente controlado e padronizado.
---

## 📚 Próximos Passos

- Implementação de **microserviços** e **API Gateway**.
- Adicionar **WebSockets** para rastreamento de pedidos em tempo real.
- Monitoramento com **Prometheus e Grafana**.
- Implementação de **caching com Redis** para otimizar desempenho.

---

