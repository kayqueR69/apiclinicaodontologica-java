package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ClienteDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ICliente dao;

    @PostMapping ("/cadastro")
    public ResponseEntity<Cliente> cadastroCliente (@RequestBody Cliente cliente) {
        // adicionando cliente no banco de dados
        Cliente novoCliente = dao.save(cliente);

        return ResponseEntity.status(201).body(novoCliente);
    }

    @PostMapping ("/login")
    public ResponseEntity<?> loginCliente (@RequestBody ClienteDTO cliente) {

        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dao.findAll();

        Map<String, Object> resposta = new HashMap<>();

        for (int c = 0; c < clientes.size(); c++) {

            if (clientes.get(c).getEmail().equalsIgnoreCase(cliente.getEmail()) &&  clientes.get(c).getSenha().equals(cliente.getSenha())) {
                resposta.put("Menssagem", "Logado no sistema!");

                return ResponseEntity.status(200).body(resposta);
            } else {
                resposta.put("Menssagem", "Login ou senha invalido!");

                return ResponseEntity.status(401).body(resposta);
            }

        }

        resposta.put("Menssagem", "Bad request!");
        return ResponseEntity.status(400).body(resposta);
    }

    @PutMapping ("/alterar")
    public ResponseEntity<?> alterarCliente (@RequestBody Cliente cliente) {

        if (cliente.getId() == 0) { // validação para o caso onde não seja passado o id no body da requisição
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("Menssagem", "Conflito de dados");
            return ResponseEntity.status(409).body(resposta);
        }

        ClienteDTO clienteSaida = new ClienteDTO();

        Cliente clienteAlterado = dao.save(cliente);
        clienteSaida.setNome(clienteAlterado.getNome());
        clienteSaida.setEmail(clienteAlterado.getEmail());
        clienteSaida.setSenha(clienteAlterado.getSenha());

        return ResponseEntity.status(200).body(clienteSaida);
    }
}
