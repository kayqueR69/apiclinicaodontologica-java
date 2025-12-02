package com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentistaRepositery extends JpaRepository<Dentista, Integer> {
    List<Dentista> findAllByAtivoTrue();
    Optional<Dentista> findByIdAndAtivoTrue(Integer id);
    Optional<Dentista> findByEmailAndAtivoTrue(String email);
}
