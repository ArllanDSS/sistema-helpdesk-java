# Sistema de Help Desk - Java Core

Este projeto é um sistema de gerenciamento de chamados técnicos desenvolvido para consolidar conhecimentos em **Orientação a Objetos** com Java.

## Tecnologias Utilizadas
- Java 17+
- Maven
- Git

## Conceitos de OO Aplicados
- **Herança e Abstração:** Classes de usuários (`Cliente` e `Tecnico`) herdando de uma classe abstrata `Usuario`.
- **Encapsulamento:** Uso de modificadores de acesso e métodos Getters/Setters.
- **Enums:** Padronização de Status e Prioridade.
- **Composição:** O objeto `Ticket` que integra Clientes e Técnicos.
- **Camada de Service:** Separação da lógica de negócio da interface principal.

## Atualização: Refatoração e Arquitetura (Fase 1.1)
Nesta etapa, apliquei princípios de **Clean Code** e **Separação de Responsabilidades**:
- **Desacoplamento:** Removi a criação de objetos (`Cliente`, `Tecnico`) da classe `Main`. Agora, a interface apenas coleta dados e a camada de `Service` gerencia a lógica de construção.
- **Sobrescrita de Métodos:** Implementei o método `toString()` na classe `Ticket` para padronizar a exibição dos dados no console, simplificando a camada de visualização.
- **Baixo Acoplamento:** O Menu não precisa conhecer as regras de criação das entidades, facilitando manutenções futuras.

## Evolução da Arquitetura: Camada de View e SRP (Fase 1.2)

Nesta atualização, o projeto deixou de ser um script linear para seguir uma arquitetura modularizada, focada no **Princípio da Responsabilidade Única (SRP)**:

- **Criação da Camada de View:** Toda a lógica de interação com o usuário (Scanner, menus e loops) foi movida para a classe `HelpDeskConsole` dentro do pacote `view`.
- **Main Minimalista:** A classe `Main` agora atua apenas como o ponto de entrada (entry point), sendo responsável exclusivamente por inicializar a aplicação.
- **Desacoplamento de Interface:** Esta estrutura permite que a interface de usuário (CLI) seja substituída no futuro por uma API REST ou interface gráfica (GUI) sem a necessidade de alterar as regras de negócio ou a classe principal.
- **Organização de Fluxo:** Implementação de métodos privados para processamento de opções, tornando o código mais legível e fácil de testar.
