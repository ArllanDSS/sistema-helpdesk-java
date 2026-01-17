# HelpDesk System - Gerenciamento de Chamados (CLI)

Este é um sistema de Help Desk desenvolvido em Java para gerenciar tickets de suporte técnico. O projeto foi construído focando em fundamentos sólidos de Orientação a Objetos, Arquitetura em Camadas e Persistência de Dados.

## 🛠️ Tecnologias e Ferramentas
- **Linguagem:** Java 17+
- **Gerenciador de Dependências:** Maven
- **Banco de Dados:** PostgreSQL
- **Persistência:** JDBC (Java Database Connectivity)
- **Versionamento:** Git

## 🏛️ Arquitetura e Padrões de Projeto
O projeto segue o padrão de **Arquitetura em Camadas**, garantindo a separação de responsabilidades:
- **Model:** Entidades puras do negócio (Ticket, Usuário, Cliente, Técnico).
- **Repository (DAO):** Camada de persistência isolada com o padrão Data Access Object.
- **Service:** Centralização das regras de negócio e validações.
- **View:** Interface de linha de comando (CLI) para interação com o usuário.

## 🚀 Funcionalidades Principais
- **Abertura de Tickets:** Registro de problemas com título, descrição e níveis de prioridade.
- **Atribuição de Técnico:** Fluxo de trabalho para vincular um técnico responsável a um chamado.
- **Ciclo de Vida do Ticket:** Gerenciamento de estados (Aberto, Em Andamento, Concluído).
- **Segurança de Dados:** Implementação de carregamento de credenciais via arquivos `.properties` e proteção via `.gitignore`.
- **Persistência Real:** Armazenamento robusto em banco de dados relacional com PreparedStatement (proteção contra SQL Injection).

## 🔧 Como Executar
1. Clone o repositório.
2. Certifique-se de ter o PostgreSQL instalado e uma base de dados criada.
3. Configure o arquivo `src/main/resources/config.properties` baseando-se no modelo `config.properties.example`.
4. Execute o Maven para baixar as dependências: `mvn install`.
5. Inicie a aplicação através da classe `Main`.