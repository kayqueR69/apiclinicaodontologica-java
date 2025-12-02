package com.ifscsal.clinicaodontologica.clinicaodotologica.DTO;

import java.util.Date;

public class ConsultaUpdateDTO {

    private int idConsulta;
    private int idDentista;
    private Date dataConsulta;
    private String motivo;

    public int getIdConsulta () {
        return this.idConsulta;
    }

    public void setIdConsulta (int idConsulta) {
        this.idConsulta = idConsulta;
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
}
