package com.arllan.helpdesk.model;

public class Cliente extends Usuario {

    public Cliente(int id, String nome, String email) {
        // O super deve ser SEMPRE a primeira linha do construtor
        super(id, nome, email);
    }
}