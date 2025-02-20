package com.ifscsal.clinicaodontologica.clinicaodotologica.DTO;

import jakarta.persistence.Column;

public record DentistaDTO(Integer id,
        String nome,
        String especialidade,
        @Column(unique = true) String email,
        String senha,
        String CRO) {
}
