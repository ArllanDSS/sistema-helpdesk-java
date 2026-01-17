package com.arllan.helpdesk.service;

import com.arllan.helpdesk.model.*;
import com.arllan.helpdesk.repository.TicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    // Simulação do banco de dados em memória
    private TicketDAO ticketDAO = new TicketDAO();

    /**
     * Méto.do para abrir um novo chamado.
     * Aqui é onde aplicamos as regras de negócio iniciais.
     */
    public void abrirTicket(String titulo, String descricao, int prioridadeCod, String nomeCliente) {
        // 1. A lógica de transformar o número (1, 2, 3) em Enum fica aqui
        Prioridade prioridade = (prioridadeCod == 3) ? Prioridade.ALTA :
                (prioridadeCod == 2) ? Prioridade.MEDIA : Prioridade.BAIXA;

        // 2. A lógica de criar o objeto Cliente fica aqui
        Cliente cliente = new Cliente(0, nomeCliente, "contato@email.com");
        // 3. Criamos o ticket
        Ticket novoTicket = new Ticket(0, titulo, descricao, prioridade, cliente);

        // 4. Salva o ticket no Banco
        ticketDAO.salvar(novoTicket);
    }


    public void atribuirTecnico(int ticketId, String nomeTecnico) {
        // Regra de negócio: Quando um técnico assume, o status passa a ser EM_ANDAMENTO
        String statusAndamento = Status.EM_ANDAMENTO.name();

        // Chamamos o DAO para persistir a alteração
        ticketDAO.atualizarTecnico(ticketId, nomeTecnico, statusAndamento);
    }
    public void finalizarTicket(int ticketId) {
        // 1. Primeiro, precisamos verificar se o ticket existe e se tem técnico
        // Buscamos a lista atualizada do banco
        List<Ticket> tickets = ticketDAO.buscarTodos();

        for (Ticket t : tickets) {
            if (t.getId() == ticketId) {
                // Regra de Ouro: Sem técnico, não finaliza!
                if (t.getTecnicoResponsavel() == null || t.getTecnicoResponsavel().getNome().isEmpty()) {
                    System.err.println("ERRO: O ticket #" + ticketId + " não pode ser finalizado sem um técnico atribuído.");
                    return;
                }

                // 2. Se estiver tudo OK, mandamos o DAO atualizar
                ticketDAO.atualizarStatus(ticketId, Status.CONCLUIDO.name());
                return;
            }
        }
        System.err.println("ERRO: Ticket #" + ticketId + " não encontrado.");
    }

    // Método para listar todos os tickets
    public List<Ticket> listarTodos() {
        // Agora buscamos direto da fonte real
        return ticketDAO.buscarTodos();
    }
}