package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.IConsulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/consulta")
public class ConsultaController {

    @Autowired
    private IConsulta daoConsulta;

    @Autowired
    private ICliente daoCliente;

    @Autowired
    private DentistaRepositery daoDentista;

    @PostMapping ("/agendar")
    public ResponseEntity<?> agendarConsulta (@RequestBody Consulta consulta){

        Cliente cliente =  daoCliente.findById(consulta.getCliente().getId()).get();
        System.out.println(cliente.getNome());

        consulta.setNomePaciente(cliente.getNome());
        consulta.setEmailPaciente(cliente.getEmail());
        consulta.setEstado("Agendado");

        Dentista dentista =   daoDentista.findById(consulta.getDentista().getId()).get();

        System.out.println(dentista.getNome());

        consulta.setNomeDentista(dentista.getNome());

        Consulta novaConsulta = daoConsulta.save(consulta);

        return ResponseEntity.status(201).build();

    }

}
