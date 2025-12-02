package com.ifscsal.clinicaodontologica.clinicaodotologica.DTO;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;

public class ClienteDTO {

    private String nome;
    private String email;
    private String senha;

    public ClienteDTO (Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
    }

    public ClienteDTO () {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
