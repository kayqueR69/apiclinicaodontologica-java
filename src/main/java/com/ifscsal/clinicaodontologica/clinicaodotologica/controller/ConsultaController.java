package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaUpdateDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.services.ConsultaService;
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
    ConsultaService consultaService;

    @PostMapping ("/agendar")
    public ResponseEntity<?> agendarConsulta (@RequestBody Consulta consulta){

        Consulta novaConsulta = consultaService.agendar(consulta);

        Map<String, Object> resposta = new HashMap<>();

        resposta.put("menssagem", "consulta agendada");
        resposta.put("agendamento", new ConsultaDTO(novaConsulta));

        return ResponseEntity.status(201).body(resposta);

    }

    @GetMapping
    public ResponseEntity<?> getConsultas () {
        ArrayList<ConsultaDTO> consultasSaida = consultaService.formatBuscar();

        return ResponseEntity.status(200).body(consultasSaida);
    }

    @GetMapping ("/cliente/{idCliente}")
    public ResponseEntity<?> getConsultasByCliente (@PathVariable int idCliente){
        return ResponseEntity.status(200).body(consultaService.buscarPorCliente(idCliente));
    }

    @GetMapping ("/dentista/{idDentista}")
    public ResponseEntity<?> getConsultasByDentista (@PathVariable int idDentista){
        return ResponseEntity.status(200).body(consultaService.buscarPorDentista(idDentista));
    }

    @PutMapping ("/alterar")
    public ResponseEntity<?> alterarConsulta (@RequestBody ConsultaUpdateDTO consulta){
        ConsultaDTO consultaRegistro = consultaService.alterar(consulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta alterada");
        resposta.put("agendamento",consultaRegistro);

        return ResponseEntity.status(200).body(resposta);
    }

    @PutMapping ("/desmarcar/{idConsulta}")
    public ResponseEntity<?> desmarcarConsulta (@PathVariable int idConsulta){
        consultaService.desmarcar(idConsulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta desmarcada");

        return ResponseEntity.status(200).body(resposta);
    }

    @PutMapping ("/finalizar/{idConsulta}")
    public ResponseEntity<?> finalizarConsulta (@PathVariable int idConsulta){
        consultaService.finalizar(idConsulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta finalizada");

        return ResponseEntity.status(200).body(resposta);

    }

    @DeleteMapping ("/deletar/{idConsulta}")
    public ResponseEntity<?> deletarConsulta (@PathVariable int idConsulta){

        ConsultaDTO consultaSaida = consultaService.deletar(idConsulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta deletada");
        resposta.put("consulta", consultaSaida);

        return ResponseEntity.status(200).body(resposta);
    }

}
