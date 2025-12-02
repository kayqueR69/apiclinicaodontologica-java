package com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import org.springframework.data.repository.CrudRepository;

public interface IConsulta extends CrudRepository<Consulta, Integer> {
}
