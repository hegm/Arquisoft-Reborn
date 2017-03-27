/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yf.rivera1851
 */

public class Consejo extends Model {

    public Long id;
    public Medico medico;
    public Paciente paciente;
    public String fecha;
    public String recomendacion;
    
    public Consejo()
    {
        
    }

    public Consejo(Medico medico, Paciente paciente, String fecha, String recomendacion) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.recomendacion = recomendacion;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
     public static Consejo bind(JsonNode j) {
        String fecha = j.findPath("fecha").asText();
        String recomendacion = j.findPath("recomendacion").asText();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        paciente.bind(j);
        medico.bind(j);
        Consejo consejo = new Consejo(medico, paciente, fecha, recomendacion);
        return consejo;
    }

    public void update(Consejo nuevoConsejo) {
        this.setFecha((nuevoConsejo.getFecha()));
        this.setRecomendacion(nuevoConsejo.getRecomendacion());
        this.setPaciente((nuevoConsejo.getPaciente()));
        this.setMedico((nuevoConsejo.getMedico()));
    }

    public void delete() {
        this.setId(null);
        this.setPaciente(null);
        this.setMedico(null);
        this.setFecha("");
        this.setRecomendacion("");
    }
    
    
    
    
    
}
