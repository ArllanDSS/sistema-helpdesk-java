package com.arllan.helpdesk.service;

import com.arllan.helpdesk.model.*;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    // Simulação do banco de dados em memória
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * Méto.do para abrir um novo chamado.
     * Aqui é onde aplicamos as regras de negócio iniciais.
     */
    public void abrirTicket(String titulo, String descricao, int prioridadeCod, String nomeCliente) {
        // 1. A lógica de transformar o número (1, 2, 3) em Enum fica aqui
        Prioridade prioridade = (prioridadeCod == 3) ? Prioridade.ALTA :
                (prioridadeCod == 2) ? Prioridade.MEDIA : Prioridade.BAIXA;

        // 2. A lógica de criar o objeto Cliente fica aqui
        // No futuro, aqui você buscaria o cliente no banco de dados pelo nome ou CPF
        Cliente cliente = new Cliente(tickets.size() + 1, nomeCliente, nomeCliente.toLowerCase() + "@email.com");

        // 3. Criamos o ticket
        Ticket novoTicket = new Ticket(100 + tickets.size(), titulo, descricao, prioridade, cliente);

        tickets.add(novoTicket);
        System.out.println("Sucesso: Ticket criado para " + nomeCliente);
    }

    public void atribuirTecnico(int ticketId, String nomeTecnico) {
        // 1. O Service cria o objeto (ou no futuro, buscaria no banco)
        Tecnico tecnico = new Tecnico(1, nomeTecnico, nomeTecnico.toLowerCase() + "@suporte.com", "Geral");

        // 2. Lógica de busca e atribuição
        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {
                t.setTecnicoResponsavel(tecnico);
                t.setStatus(Status.EM_ANDAMENTO);
                System.out.println("O técnico " + nomeTecnico + " agora é o responsável pelo ticket #" + ticketId);
                return;
            }
        }
        System.err.println("Ticket #" + ticketId + " não encontrado.");
    }

    public void finalizarTicket(int ticketId) {
        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {

                // Regra de Negócio: O chamado só pode ser finalizado se tiver um técnico
                if (t.getTecnicoResponsavel() == null) {
                    System.err.println("Erro: Não é possível finalizar o chamado #" + ticketId + " sem um técnico atribuído.");
                    return;
                }

                t.setStatus(Status.CONCLUIDO);
                System.out.println("Sucesso: Chamado #" + ticketId + " foi FINALIZADO com sucesso.");
                return;
            }
        }
        System.err.println("Erro: Chamado #" + ticketId + " não encontrado para finalização.");
    }

    // Método para listar todos os tickets
    public List<Ticket> listarTodos() {
        return this.tickets;
    }
}