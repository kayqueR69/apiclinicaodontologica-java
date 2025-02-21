package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.IConsulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

        Cliente cliente =  daoCliente.findById(consulta.getCliente().getId()).get();
        consulta.setNomePaciente(cliente.getNome());
        consulta.setEmailPaciente(cliente.getEmail());
        consulta.setEstado("Agendado");

        Dentista dentista =   daoDentista.findById(consulta.getDentista().getId()).get();
        consulta.setNomeDentista(dentista.getNome());

        Consulta novaConsulta = daoConsulta.save(consulta);

        Map<Object, Object> resposta = new HashMap<Object, Object>();

        resposta.put("menssagem", "consulta agendada");
        resposta.put("agendamento", new ConsultaDTO(novaConsulta));

        return ResponseEntity.status(201).body(resposta);

    }

    @GetMapping
    public ResponseEntity<?> getConsultas () {
        ArrayList<Consulta> consultas = (ArrayList<Consulta>) daoConsulta.findAll();
        ArrayList<ConsultaDTO> consultasSaida = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            consultasSaida.add(new ConsultaDTO(consultas.get(c)));
        }

        return ResponseEntity.status(200).body(consultasSaida);
    }

    @GetMapping ("/cliente/{id}")
    public ResponseEntity<?> getConsultasByCliente (@PathVariable int id){
        ArrayList<Consulta> consultas = (ArrayList<Consulta>) daoConsulta.findAll();

        ArrayList<ConsultaDTO> consultasCliente = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            if (consultas.get(c).getCliente().getId() == id) {
                consultasCliente.add(new ConsultaDTO(consultas.get(c)));
            }
        }

        return ResponseEntity.status(200).body(consultasCliente);
    }

    @GetMapping ("/dentista/{id}")
    public ResponseEntity<?> getConsultasByDentista (@PathVariable int id){

        ArrayList<Consulta> consultas = (ArrayList<Consulta>) daoConsulta.findAll();

        ArrayList<ConsultaDTO> consultasDentista = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            if (consultas.get(c).getDentista().getId() == id) {
                consultasDentista.add(new ConsultaDTO(consultas.get(c)));
            }
        }

        return ResponseEntity.status(200).body(consultasDentista);
    }

}
