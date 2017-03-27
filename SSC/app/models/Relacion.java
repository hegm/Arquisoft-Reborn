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

public class Relacion extends Model {

    public Long id;
    
    public Medico medico;
    public Paciente paciente;

    public Relacion(Medico medico, Paciente paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }
    
   public Relacion()
   {
       
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
   
   
    
    
    public static Relacion bind(JsonNode j) {
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        paciente.bind(j);
        medico.bind(j);
        Relacion relacion = new Relacion(medico, paciente);
    return relacion;
    }

    
}
