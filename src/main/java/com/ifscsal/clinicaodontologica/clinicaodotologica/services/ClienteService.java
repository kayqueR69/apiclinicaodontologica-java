package com.ifscsal.clinicaodontologica.clinicaodotologica.services;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ClienteDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.IConsulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.observer.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService implements Observable {

    @Autowired
    private ICliente dao;

    @Autowired
    private IConsulta daoConsulta;

    // implementando o padrão de projeto observer
    private ArrayList<Consulta> consultas;

    @Autowired
    private ConsultaService consultaService;

    @Override
    public void addObservers (int id) {
        consultas = new ArrayList<>();
        ArrayList<Consulta> consultasCliente = consultaService.buscar();

        for (int c = 0; c < consultasCliente.size(); c++) {
            if (consultasCliente.get(c).getCliente().getId() == id) {
                consultas.add(consultasCliente.get(c));
            }
        }

    };

    @Override
    public void notificarObservers(String acao, Cliente cliente) {
        for (int c = 0; c < consultas.size(); c++) {
            if (acao.equals("alterar")) {
                consultaService.update(cliente, consultas.get(c));
            } else if (acao.equals("cancelar")) {
                consultaService.desmarcar(consultas.get(c));
            }
        }
    }

    public  ClienteService(ICliente daoCliente) {
        this.dao = daoCliente;
    }

    public Cliente cadastrar (Cliente cliente) {
        Cliente novoCliente = dao.save(cliente);
        return novoCliente;
    }

    public Cliente validacaoEmailSenha(String email, String senha) {
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) dao.findAll();

        for (int c = 0; c < clientes.size(); c++) {
            if (clientes.get(c).getEmail().equals(email) &&  clientes.get(c).getSenha().equals(senha)) {
                return clientes.get(c);
            }
        }

        return null;
    }

    public ClienteDTO alterar (Cliente cliente) {

        Cliente clienteRegistro = dao.findById(cliente.getId()).get();

        if (cliente.getNome() != null) clienteRegistro.setNome(cliente.getNome());

        if (cliente.getEmail() != null) clienteRegistro.setEmail(cliente.getEmail());

        if (cliente.getSenha() != null) clienteRegistro.setSenha(cliente.getSenha());

        ClienteDTO clienteSaida = new ClienteDTO(clienteRegistro);

        dao.save(clienteRegistro);

        ArrayList<Consulta> consultas = (ArrayList<Consulta>) daoConsulta.findAll();
        addObservers(cliente.getId());
        notificarObservers("alterar", clienteRegistro);

        return clienteSaida;

    }

    public Cliente deletar (int id) {

        Cliente clienteDeletado = dao.findById(id).get();

        dao.delete(clienteDeletado);

        notificarObservers("cancelar", clienteDeletado);

        return clienteDeletado;

    }

}
