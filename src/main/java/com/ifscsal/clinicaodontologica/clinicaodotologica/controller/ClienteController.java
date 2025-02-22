package com.ifscsal.clinicaodontologica.clinicaodotologica.controller;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ClienteDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.IConsulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping ("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IConsulta daoConsulta;


    @PostMapping ("/cadastro")
    public ResponseEntity<Cliente> cadastroCliente (@RequestBody Cliente cliente) {

        Cliente novoCliente = clienteService.cadastrar(cliente);

        return ResponseEntity.status(201).body(novoCliente);
    }

    @PostMapping ("/login")
    public ResponseEntity<?> loginCliente (@RequestBody ClienteDTO cliente) {

            Map<String,Object> resposta = new HashMap<>();

            if (clienteService.validacaoEmailSenha(cliente.getEmail(), cliente.getSenha()) != null) {
                resposta.put("Menssagem", "Logado no sistema!");

                return ResponseEntity.status(200).body(resposta);
            } else {
                resposta.put("Menssagem", "Login ou senha invalido!");

                return ResponseEntity.status(401).body(resposta);
            }

    }

    @PutMapping ("/alterar")
    public ResponseEntity<?> alterarCliente (@RequestBody Cliente cliente) {
        Map<String, Object> resposta = new HashMap<>();

        if (cliente.getId() == 0) { // validação para o caso onde não seja passado o id no body da requisição
            resposta.put("Menssagem", "Conflito de dados");
            return ResponseEntity.status(409).body(resposta);
        }

        ClienteDTO clienteSaida = clienteService.alterar(cliente);

        resposta.put("Menssagem", "Alterado no sistema!");
        resposta.put("Cliente", clienteSaida);


        return ResponseEntity.status(200).body(resposta);
    }

    @DeleteMapping ("/cliente/deletar/{id}")
    public ResponseEntity<?> deletarCliente (@PathVariable int id) {
        Cliente clienteDelete = clienteService.deletar(id);

        Map <String, Object> resposta = new HashMap<>();

        resposta.put("menssagem", "cliente deletado");
        resposta.put("cliente", clienteDelete);

        return ResponseEntity.status(204).body(resposta);
    }
}
