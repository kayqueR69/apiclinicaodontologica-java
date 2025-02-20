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

import java.util.HashMap;
import java.util.Map;

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
        // work in progress : necessário de tratar o objeto consulta da resposta

        Cliente cliente =  daoCliente.findById(consulta.getCliente().getId()).get();
        consulta.setNomePaciente(cliente.getNome());
        consulta.setEmailPaciente(cliente.getEmail());
        consulta.setEstado("Agendado");

        Dentista dentista =   daoDentista.findById(consulta.getDentista().getId()).get();
        consulta.setNomeDentista(dentista.getNome());

        Consulta novaConsulta = daoConsulta.save(consulta);

        Map<Object, Object> resposta = new HashMap<Object, Object>();

        resposta.put("menssagem", "consulta agendada");
        resposta.put("agendamento", novaConsulta);

        return ResponseEntity.status(201).body(resposta);

    }

}
