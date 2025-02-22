package com.ifscsal.clinicaodontologica.clinicaodotologica.model.entidades;

import com.ifscsal.clinicaodontologica.clinicaodotologica.damain.dentista.Dentista;
import com.ifscsal.clinicaodontologica.clinicaodotologica.observer.Observer;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "consulta")
public class Consulta {

    // - atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idConsulta")
    private int idConsulta;

    @ManyToOne
    @JoinColumn (name = "idCliente", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn (name = "idDentista", referencedColumnName = "id")
    private Dentista dentista;

    @Column (name = "dataConsulta")
    private Date dataConsulta;

    @Column (name = "motivo", length = 200, nullable = false)
    private String motivo;

    @Column (name = "estado", length = 50, nullable = false)
    private String estado;

    @Column (name = "nomePaciente", length = 50, nullable = false)
    private String nomePaciente;

    @Column (name = "emailPaciente", length = 200, nullable = false)
    private String emailPaciente;

    @Column (name = "nomeDentista", length = 50, nullable = false)
    private String nomeDentista;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNomeDentista() {
        return nomeDentista;
    }

    public void setNomeDentista(String nomeDentista) {
        this.nomeDentista = nomeDentista;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }
}
