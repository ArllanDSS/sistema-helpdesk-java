package com.arllan.helpdesk.model;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataAbertura;

    // Enums
    private Prioridade prioridade;
    private Status status;

    // Composição: Um Ticket TEM um Cliente e um Tecnico
    private Cliente cliente;
    private Tecnico tecnicoResponsavel;

    public Ticket(int id, String titulo, String descricao, Prioridade prioridade, Cliente cliente) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.cliente = cliente;

        // Regras automáticas ao criar um chamado:
        this.status = Status.ABERTO; // Todos os chamados nascem abertos
        this.dataAbertura = LocalDateTime.now(); // Pega a data e hora atual
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnicoResponsavel() {
        return tecnicoResponsavel;
    }

    public void setTecnicoResponsavel(Tecnico tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }
}