package com.arllan.helpdesk.model;

/**
 * Classe abstrata que representa a base de todos os usuários do sistema.
 * Não pode ser instanciada diretamente (regra de Orientação a Objetos).
 */
public abstract class Usuario {

    // Atributos privados garantem o Encapsulamento
    private int id;
    private String nome;
    private String email;

    // Construtor: usado pelas classes filhas para inicializar os dados
    public Usuario(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters: a forma segura de acessar e alterar os dados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}