package com.ifscsal.clinicaodontologica.clinicaodotologica.DTO;

import com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades.Consulta;

import java.util.Date;

public class ConsultaDTO {

    private int idConsulta;
    private int idCliente;
    private int idDentista;
    private Date dataConsulta;
    private String motivo;
    private String estado;
    private String nomePaciente;
    private String emailPaciente;
    private String nomeDentista;

    public ConsultaDTO () {}

    public ConsultaDTO(int idConsulta, int idCliente, int idDentista, Date dataConsulta, String motivo, String estado, String nomePaciente, String emailPaciente, String nomeDentista) {
        this.idConsulta = idConsulta;
        this.idCliente = idCliente;
        this.idDentista = idDentista;
        this.dataConsulta = dataConsulta;
        this.motivo = motivo;
        this.estado = estado;
        this.nomePaciente = nomePaciente;
        this.emailPaciente = emailPaciente;
        this.nomeDentista = nomeDentista;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(int idDentista) {
        this.idDentista = idDentista;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getNomeDentista() {
        return nomeDentista;
    }

    public void setNomeDentista(String nomeDentista) {
        this.nomeDentista = nomeDentista;
    }

    // formatando objeto consulta para saida
    public ConsultaDTO formatConsulta (Consulta consulta) {

        return new ConsultaDTO(
                consulta.getIdConsulta(),
                consulta.getCliente().getId(),
                consulta.getDentista().getId(),
                consulta.getDataConsulta(),
                consulta.getMotivo(),
                consulta.getEstado(),
                consulta.getNomePaciente(),
                consulta.getEmailPaciente(),
                consulta.getNomeDentista()
        );

    }

}
