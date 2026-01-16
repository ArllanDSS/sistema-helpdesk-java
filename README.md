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