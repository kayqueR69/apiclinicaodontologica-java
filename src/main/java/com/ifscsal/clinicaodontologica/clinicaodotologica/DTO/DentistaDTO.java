package com.ifscsal.clinicaodontologica.clinicaodotologica.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DentistaDTO(
        Integer id,
        @NotNull @NotBlank String nome,
        @NotBlank String especialidade,
        @NotNull @NotBlank @Column(unique = true) String email,
        @NotNull String senha,
        @NotNull String CRO) {
}

