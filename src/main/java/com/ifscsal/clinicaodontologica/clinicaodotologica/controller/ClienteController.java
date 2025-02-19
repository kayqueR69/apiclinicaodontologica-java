package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ICliente dao;

    @PostMapping
    public ResponseEntity<Cliente> cadastroCliente (@RequestBody Cliente cliente) {
        // adicionando cliente no banco de dados
        Cliente novoCliente = dao.save(cliente);

        return ResponseEntity.status(201).body(novoCliente);
    }
}
