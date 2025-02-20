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
    private String CRO;

    public Dentista(DentistaDTO dentistaDTO) {
        this.nome = dentistaDTO.nome();
        this.email = dentistaDTO.email();
        this.especialidade = dentistaDTO.especialidade();
        this.senha = dentistaDTO.senha();
        this.CRO = dentistaDTO.CRO();
    }
}