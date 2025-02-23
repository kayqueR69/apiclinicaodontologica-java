package com.ifscsal.clinicaodontologica.clinicaodotologica.services;

import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.DTO.ConsultaUpdateDTO;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.DentistaRepositery;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.ICliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.DAO.IConsulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Cliente;
import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;
import com.ifscsal.clinicaodontologica.clinicaodotologica.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConsultaService implements Observer {

    @Autowired
    private IConsulta dao;

    @Autowired
    private ICliente daoCliente;

    @Autowired
    private DentistaRepositery daoDentista;

    public Consulta agendar (ConsultaDTO consulta){

        Cliente cliente =  daoCliente.findById(consulta.getIdCliente()).get();
        consulta.setNomePaciente(cliente.getNome());
        consulta.setEmailPaciente(cliente.getEmail());
        consulta.setEstado("Agendado");

        Dentista dentista = daoDentista.findById(consulta.getIdDentista()).get();
        consulta.setNomeDentista(dentista.getNome());

        Consulta novaConsulta = new Consulta();
        novaConsulta.setCliente(cliente);
        novaConsulta.setDentista(dentista);
        novaConsulta.setDataConsulta(consulta.getDataConsulta());
        novaConsulta.setMotivo(consulta.getMotivo());
        novaConsulta.setEstado(consulta.getEstado());
        novaConsulta.setNomePaciente(consulta.getNomePaciente());
        novaConsulta.setEmailPaciente(consulta.getEmailPaciente());
        novaConsulta.setNomeDentista(consulta.getNomeDentista());

        dao.save(novaConsulta);

        return novaConsulta;

    }

    public ArrayList<Consulta> buscar (){
        ArrayList<Consulta> consultas = (ArrayList<Consulta>) dao.findAll();
        return consultas;
    }

    public ArrayList<ConsultaDTO> formatBuscar () {
        ArrayList<Consulta> consultas = (ArrayList<Consulta>) dao.findAll();
        ArrayList<ConsultaDTO> consultasSaida = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            consultasSaida.add(new ConsultaDTO(consultas.get(c)));
        }

        return consultasSaida;
    }

    public ArrayList<ConsultaDTO> buscarPorCliente (int idCliente) {
        ArrayList<Consulta> consultas = (ArrayList<Consulta>) dao.findAll();

        ArrayList<ConsultaDTO> consultasCliente = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            if (consultas.get(c).getCliente() != null && consultas.get(c).getCliente().getId() == idCliente) {
                consultasCliente.add(new ConsultaDTO(consultas.get(c)));
            }
        }

        return consultasCliente;
    }

    public ArrayList<ConsultaDTO> buscarPorDentista (int idDentista) {

        ArrayList<Consulta> consultas = (ArrayList<Consulta>) dao.findAll();

        ArrayList<ConsultaDTO> consultasDentista = new ArrayList<>();

        for (int c = 0; c < consultas.size(); c++) {
            if (consultas.get(c).getDentista() != null && consultas.get(c).getDentista().getId() == idDentista) {
                consultasDentista.add(new ConsultaDTO(consultas.get(c)));
            }
        }

        return consultasDentista;
    }

    public ConsultaDTO alterar (ConsultaUpdateDTO consulta) {

        Consulta consultaRegistro = dao.findById(consulta.getIdConsulta()).get();

        if (consultaRegistro.getDentista().getId() != consulta.getIdDentista() && consulta.getIdDentista() != 0){
            consultaRegistro.setDentista(daoDentista.getReferenceById(consulta.getIdDentista()));
            consultaRegistro.setNomeDentista(daoDentista.findById(consulta.getIdDentista()).get().getNome());
        }

        if (consulta.getDataConsulta() != null) consultaRegistro.setDataConsulta(consulta.getDataConsulta());

        if (consulta.getMotivo() != null) consultaRegistro.setMotivo(consulta.getMotivo());

        dao.save(consultaRegistro);

        return new ConsultaDTO(consultaRegistro);
    }

    public void desmarcar (int idConsulta){
        Consulta consulta = dao.findById(idConsulta).get();

        consulta.setEstado("cancelada");

        dao.save(consulta);
    }

    public void finalizar (int idConsulta) {
        Consulta consulta = dao.findById(idConsulta).get();

        consulta.setEstado("finalizada");
        dao.save(consulta);
    }

    public ConsultaDTO deletar (int idConsulta) {
        Consulta consultaDeletada = dao.findById(idConsulta).get();
        ConsultaDTO consultaSaida =  new ConsultaDTO(consultaDeletada);

        dao.delete(consultaDeletada);

        return consultaSaida;
    }

    @Override
    public void update(Cliente cliente, Consulta consulta) {

        if (!cliente.getNome().equals(consulta.getNomePaciente())) consulta.setNomePaciente(cliente.getNome());
        if (!cliente.getEmail().equals(consulta.getEmailPaciente())) consulta.setEmailPaciente(cliente.getEmail());
        System.out.println(consulta.getNomePaciente());
        dao.save(consulta);

    }

    @Override
    public void desmarcar(Consulta consulta) {
        consulta.setCliente(null);
        consulta.setEstado("cancelada");
        consulta.setNomePaciente("cadastro deletado");
        consulta.setEmailPaciente("");
        dao.save(consulta);
    }
}
