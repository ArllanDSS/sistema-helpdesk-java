package com.arllan.helpdesk.view;

import com.arllan.helpdesk.service.TicketService;
import java.util.Scanner;
import com.arllan.helpdesk.model.Ticket;

public class HelpDeskConsole {
    private Scanner scanner = new Scanner(System.in);
    private TicketService ticketService = new TicketService();

    public void exibirMenu() {
        int opcao = 0;
        System.out.println("=== SISTEMA ARLLAN HELPDESK OPERACIONAL ===");

        while (opcao != 5) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Abrir Novo Chamado");
            System.out.println("2. Atribuir Técnico");
            System.out.println("3. Finalizar Chamado");
            System.out.println("4. Listar Todos");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            processarOpcao(opcao);
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Descrição: ");
                String desc = scanner.nextLine();
                System.out.print("Prioridade (1-3): ");
                int p = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nome do Cliente: ");
                String nomeCli = scanner.nextLine();
                ticketService.abrirTicket(titulo, desc, p, nomeCli);
                break;
            case 2:
                System.out.println("\n--- ATRIBUIR TÉCNICO A CHAMADO ---");

                // 1. Coleta o ID do ticket
                System.out.print("Digite o ID do chamado: ");
                int idAtribuir = scanner.nextInt();
                scanner.nextLine(); // Limpeza obrigatória do buffer após ler um número!

                // 2. Coleta o nome do técnico
                System.out.print("Nome do técnico que assumirá o caso: ");
                String nomeTecnico = scanner.nextLine();

                // 3. Envia para o service
                // O Menu não sabe nada sobre SQL ou DAOs, ele só "passa a bola"
                ticketService.atribuirTecnico(idAtribuir, nomeTecnico);
                break;
            case 4:
                System.out.println("\n--- LISTAGEM ---");
                ticketService.listarTodos().forEach(System.out::println);
                break;
            case 3:
                System.out.println("\n--- FINALIZAR CHAMADO ---");
                System.out.print("Digite o ID do chamado que deseja encerrar: ");
                int idFinalizar = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                ticketService.finalizarTicket(idFinalizar);
                break;
            case 5:
                System.out.println("Deslogando...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}