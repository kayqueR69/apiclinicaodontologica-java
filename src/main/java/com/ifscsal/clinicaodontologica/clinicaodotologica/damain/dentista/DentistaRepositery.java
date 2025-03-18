package com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepositery extends JpaRepository<Dentista, Integer> {
    Optional<Dentista> findByEmail(String email);
}
