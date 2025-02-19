package com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades;

import jakarta.persistence.*;

@Entity
@Table (name = "cliente")
public class Cliente {

    // atibutos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column (name = "nome", length = 50)
    private String nome;

    @Column (name = "email", unique = true, nullable = false, length = 200)
    private String email;

    @Column (name = "senha", length = 200)
    private String senha;

    // metodos

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
