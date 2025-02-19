package com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.DentistaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "dentista")
@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Dentista{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String especialidade;
    private String email;
    private String senha;
    private String CRO;

    public Dentista() {}

    public Dentista(DentistaDTO dentistaDTO) {
        this.nome = dentistaDTO.nome();
        this.email = dentistaDTO.email();
        this.senha = dentistaDTO.senha();
        this.CRO = dentistaDTO.CRO();
    }
}