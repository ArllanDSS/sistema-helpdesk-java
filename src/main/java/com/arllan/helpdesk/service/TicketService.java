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
    public void abrirTicket(int id, String titulo, String descricao, Prioridade prioridade, Cliente cliente) {

        // Validação Simples
        if (titulo == null || titulo.trim().isEmpty()) {
            System.err.println("Erro: Não é possível abrir um ticket sem título.");
            return;
        }

        if (cliente == null) {
            System.err.println("Erro: Todo ticket precisa estar associado a um cliente.");
            return;
        }

        // Criamos o objeto Ticket
        Ticket novoTicket = new Ticket(id, titulo, descricao, prioridade, cliente);

        // Adicionamos à nossa lista (simulando o "Save" no banco)
        tickets.add(novoTicket);

        System.out.println("Sucesso: Ticket #" + id + " criado para o cliente " + cliente.getNome());
    }

    public void atribuirTecnico(int ticketId, Tecnico tecnico) {
        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {
                t.setTecnicoResponsavel(tecnico);
                t.setStatus(Status.EM_ANDAMENTO); // Regra: Se tem técnico, o status muda!
                System.out.println("Técnico " + tecnico.getNome() + " assumiu o chamado #" + ticketId);
                return;
            }
        }
        System.err.println("Erro: Chamado #" + ticketId + " não encontrado.");
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