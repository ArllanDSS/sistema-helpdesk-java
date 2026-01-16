package com.arllan.helpdesk;

import com.arllan.helpdesk.model.Cliente;
import com.arllan.helpdesk.model.Prioridade;
import com.arllan.helpdesk.model.Tecnico;
import com.arllan.helpdesk.model.Ticket;
import com.arllan.helpdesk.service.TicketService;

public class Main {
    public static void main(String[] args) {
        // 1. Iniciamos o serviço (o cérebro do sistema)
        TicketService ticketService = new TicketService();

        // 2. Criamos um cliente para testar
        // Imagine que este é um usuário buscando suporte técnico
        Cliente clienteTeste = new Cliente(1, "Arllan", "contato@arllan.dev");

        System.out.println("--- Iniciando Simulação de Sistema de Help Desk ---");

        // 3. Abrimos o primeiro ticket através do service
        ticketService.abrirTicket(
                101,
                "Erro na conexão de rede",
                "O cliente informa que a luz 'system' está apagada.",
                Prioridade.ALTA,
                clienteTeste
        );

        // 4. Tentativa de abrir um ticket inválido (Teste de Regra de Negócio)
        System.out.println("\nTentando abrir ticket sem título:");
        ticketService.abrirTicket(102, "", "Sem descrição", Prioridade.BAIXA, clienteTeste);

        // 5. Listamos todos os chamados para confirmar que o válido foi salvo
        System.out.println("\n--- Lista de Chamados Ativos ---");
        for (Ticket t : ticketService.listarTodos()) {
            System.out.println("ID: " + t.getId() + " | Título: " + t.getTitulo() + " | Status: " + t.getStatus());
        }

        // 6. Criamos um Técnico para trabalhar no sistema
        // Aqui você pode até se colocar como o técnico do sistema!
        Tecnico tecnicoResponsavel = new Tecnico(1, "Arllan David", "suporte@arllan.com", "Redes");

        // 7. Atribuímos o técnico ao chamado #101
        System.out.println("\n--- Atribuindo Técnico ---");
        ticketService.atribuirTecnico(101, tecnicoResponsavel);

        // 8. Listamos novamente para ver a mudança
        System.out.println("\n--- Status Atualizado do Chamado ---");
        for (Ticket t : ticketService.listarTodos()) {
            String nomeTecnico = (t.getTecnicoResponsavel() != null) ? t.getTecnicoResponsavel().getNome() : "Não atribuído";
            System.out.println("ID: " + t.getId() +
                    " | Status: " + t.getStatus() +
                    " | Responsável: " + nomeTecnico);
        }

        // 9. Finalizando o chamado
        System.out.println("\n--- Finalizando o Chamado ---");
        ticketService.finalizarTicket(101);

        // 10. Verificação Final
        System.out.println("\n--- Relatório Final do Chamado ---");
        for (Ticket t : ticketService.listarTodos()) {
            System.out.println("Chamado: " + t.getTitulo());
            System.out.println("Status Final: " + t.getStatus());
            System.out.println("Responsável: " + t.getTecnicoResponsavel().getNome());
        }

    }
}