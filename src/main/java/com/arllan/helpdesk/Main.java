package com.arllan.helpdesk;

import com.arllan.helpdesk.model.*;
import com.arllan.helpdesk.service.TicketService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketService ticketService = new TicketService();
        int opcao = 0;

        System.out.println("=== BEM-VINDO AO SISTEMA ARLLAN HELPDESK ===");

        while (opcao != 5) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Abrir Novo Chamado");
            System.out.println("2. Atribuir Técnico a Chamado");
            System.out.println("3. Finalizar Chamado");
            System.out.println("4. Listar Todos os Chamados");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Prioridade (1-BAIXA, 2-MÉDIA, 3-ALTA): ");
                    int p = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do Cliente: ");
                    String nomeCli = scanner.nextLine();

                    // O Main apenas envia os dados "crus" para o Service
                    ticketService.abrirTicket(titulo, desc, p, nomeCli);
                    break;

                case 2:
                    System.out.print("ID do Chamado: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // limpa buffer
                    System.out.print("Nome do Técnico: ");
                    String nomeTec = scanner.nextLine();

                    // O Main não sabe o que é um objeto "Tecnico", ele só passa o nome
                    ticketService.atribuirTecnico(id, nomeTec);
                    break;

                case 3:
                    System.out.print("ID do Chamado para Finalizar: ");
                    int idFinalizar = scanner.nextInt();
                    ticketService.finalizarTicket(idFinalizar);
                    break;

                case 4:
                    System.out.println("\n--- LISTAGEM DE CHAMADOS ---");
                    for (Ticket t : ticketService.listarTodos()) {
                        System.out.println(t);
                    }
                    break;

                case 5:
                    System.out.println("Sistema encerrado. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}