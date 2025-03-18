package com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.DentistaDTO;
import jakarta.persistence.*;

import lombok.*;

@Table(name = "dentista")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dentista{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "especialidade", length = 50)
    private String especialidade;

    @Column(name = "email", length = 200, unique = true)
    private String email;

    @Column(name = "senha", length = 200)
    private String senha;

    @Column(name = "cro", length = 50)
    private String cro;

    public Dentista(DentistaDTO dentistaDTO) {
        this.nome = dentistaDTO.nome();
        this.email = dentistaDTO.email();
        this.especialidade = dentistaDTO.especialidade();
        this.senha = dentistaDTO.senha();
        this.cro = dentistaDTO.cro();
    }

    private Dentista(String nome, String email, String especialidade, String senha, String cro) {
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
        this.senha = senha;
        this.cro = cro;
    }

    public static class DentistaBuilder{

        private String nome;
        private String email;
        private String especialidade;
        private String senha;
        private String cro;

        public DentistaBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public DentistaBuilder email(String email) {
            this.email = email;
            return this;
        }

        public DentistaBuilder especialidade(String especialidade) {
            this.especialidade = especialidade;
            return this;
        }

        public DentistaBuilder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public DentistaBuilder cro(String cro) {
            this.cro = cro;
            return this;
        }

        public Dentista build() {
            return new Dentista(nome, email, especialidade, senha, cro);
        }
    }
}