package com.arllan.helpdesk.repository;

import com.arllan.helpdesk.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public void salvar(Ticket ticket) {
        String sql = "INSERT INTO tickets (titulo, descricao, prioridade, status, nome_cliente) VALUES (?, ?, ?, ?, ?)";

        // Try-with-resources: Garante que a conexão fechará sozinha mesmo se der erro
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ticket.getTitulo());
            stmt.setString(2, ticket.getDescricao());
            stmt.setString(3, ticket.getPrioridade().name()); // Pegamos o nome do Enum
            stmt.setString(4, ticket.getStatus().name());
            stmt.setString(5, ticket.getCliente().getNome());

            stmt.execute();
            System.out.println("LOG: Ticket salvo no PostgreSQL com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar ticket no banco", e);
        }
    }

    public List<Ticket> buscarTodos() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM tickets ORDER BY id DESC"; // Os mais novos primeiro

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // 1. Extraímos os dados da linha atual do banco
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                // 2. Convertemos as Strings de volta para Enums
                Prioridade prioridade = Prioridade.valueOf(rs.getString("prioridade"));
                Status status = Status.valueOf(rs.getString("status"));

                // 3. Criamos o Cliente (mesmo que simplificado)
                String nomeCliente = rs.getString("nome_cliente");
                Cliente cliente = new Cliente(0, nomeCliente, "");

                // 4. Montamos o objeto Ticket
                Ticket ticket = new Ticket(id, titulo, descricao, prioridade, cliente);
                ticket.setStatus(status); // Ajustamos o status que veio do banco

                // 5. Se houver um técnico no banco, adicionamos ao objeto
                String nomeTec = rs.getString("nome_tecnico");
                if (nomeTec != null) {
                    ticket.setTecnicoResponsavel(new Tecnico(0, nomeTec, "", "Geral"));
                }

                // 6. Adicionamos o objeto pronto à nossa lista
                lista.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tickets no banco", e);
        }
        return lista;
    }

    public void atualizarTecnico(int ticketId, String nomeTecnico, String novoStatus) {
        // O comando UPDATE precisa sempre de um WHERE para não afetar a tabela inteira
        String sql = "UPDATE tickets SET nome_tecnico = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTecnico);
            stmt.setString(2, novoStatus);
            stmt.setInt(3, ticketId);

            int linhasAfetadas = stmt.executeUpdate(); // executeUpdate retorna quantas linhas foram alteradas

            if (linhasAfetadas > 0) {
                System.out.println("LOG: Base de dados atualizada. Técnico " + nomeTecnico + " atribuído ao ticket #" + ticketId);
            } else {
                System.err.println("LOG: Nenhum ticket encontrado com o ID: " + ticketId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar técnico no banco", e);
        }
    }

    public void atualizarStatus(int ticketId, String novoStatus) {
        String sql = "UPDATE tickets SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, ticketId);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("LOG: Status do ticket #" + ticketId + " atualizado para " + novoStatus);
            } else {
                System.err.println("LOG: Erro ao localizar ticket #" + ticketId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao finalizar ticket no banco", e);
        }
    }

}