package com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ICliente extends CrudRepository<Cliente, Integer> {
}
