package com.ifscsal.clinicaodontologica.clinicaodotologica.observer;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;

import java.util.ArrayList;

public interface Observable {

    public void addObservers(int id);

    public void notificarObservers(String acao, Cliente cliente);

}
