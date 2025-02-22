package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaUpdateDTO;
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

    @PutMapping ("/alterar")
    public ResponseEntity<?> alterarConsulta (@RequestBody ConsultaUpdateDTO consulta){
        Consulta consultaRegistro = daoConsulta.findById(consulta.getIdConsulta()).get();

        if (consultaRegistro.getDentista().getId() != consulta.getIdDentista() && consulta.getIdDentista() != 0){
            consultaRegistro.setDentista(daoDentista.getReferenceById(consulta.getIdDentista()));
            consultaRegistro.setNomeDentista(daoDentista.findById(consulta.getIdDentista()).get().getNome());
        }

        if (consulta.getDataConsulta() != null) consultaRegistro.setDataConsulta(consulta.getDataConsulta());

        if (consulta.getMotivo() != null) consultaRegistro.setMotivo(consulta.getMotivo());

        daoConsulta.save(consultaRegistro);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta alterada");
        resposta.put("agendamento", new ConsultaDTO(consultaRegistro));

        return ResponseEntity.status(200).body(resposta);
    }

    @PutMapping ("/desmarcar/{idConsulta}")
    public ResponseEntity<?> desmarcarConsulta (@PathVariable int idConsulta){
        Consulta consulta = daoConsulta.findById(idConsulta).get();

        consulta.setEstado("cancelada");

        daoConsulta.save(consulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta desmarcada");

        return ResponseEntity.status(200).body(resposta);
    }

    @PutMapping ("/finalizar/{idConsulta}")
    public ResponseEntity<?> finalizarConsulta (@PathVariable int idConsulta){
        Consulta consulta = daoConsulta.findById(idConsulta).get();

        consulta.setEstado("finalizada");
        daoConsulta.save(consulta);

        Map <String, Object> resposta = new HashMap<>();
        resposta.put("menssagem", "consulta finalizada");

        return ResponseEntity.status(200).body(resposta);

    }

}
