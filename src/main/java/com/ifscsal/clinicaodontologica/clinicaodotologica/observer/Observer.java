package com.ifscsal.clinicaodontologica.clinicaodotologica.observer;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;

public interface Observer {

    public void update(Cliente cliente, Consulta consulta);

    public void desmarcar (Consulta consulta);

}
