package com.arllan.helpdesk.model;

public class Tecnico extends Usuario {

    private String especialidade;

    public Tecnico(int id, String nome, String email, String especialidade) {
        // Envia o básico para a classe pai (Usuario)
        super(id, nome, email);
        // Guarda o específico aqui na classe filha (Técnico)
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}