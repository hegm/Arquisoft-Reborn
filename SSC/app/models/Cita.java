/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;



public class Cita extends Model {


    public Long id;

    public String nombreMedico;

    public String nombrePaciente;

    public String horario;

    public String estado;


    public Cita(String nombreMedico, String nombrePaciente, String horario, String estado) {
        this.nombreMedico = nombreMedico;
        this.nombrePaciente = nombrePaciente;
        this.horario = horario;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static Cita bind(JsonNode j) {
        String nombreMedico = j.findPath("nombreMedico").asText();
        String nombrePaciente = j.findPath("nombrePaciente").asText();
        String horario = j.findPath("horario").asText();
        String estado = j.findPath("estado").asText();
        Cita cita = new Cita(nombreMedico, nombrePaciente, horario, estado);
        return cita;
    }

    public void update(Cita nuevaCita) {
        this.setNombreMedico((nuevaCita.getNombreMedico()));
        this.setNombrePaciente(nuevaCita.getNombrePaciente());
        this.setHorario((nuevaCita.horario));
        this.setEstado((nuevaCita.estado));
    }

    public void delete() {
        this.setId(null);
        this.setNombreMedico("");
        this.setNombrePaciente("");
        this.setHorario("");
        this.setEstado("");
    }

}
