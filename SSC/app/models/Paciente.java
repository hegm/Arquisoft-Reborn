
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacienteentity")
public class Paciente extends Model {


    public static Finder<Long,Paciente> FINDER = new Finder<>(Paciente.class);
    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Paciente")

    public Long id;

    public String nombre;

    public String apellido;

    public int edad;

    public String sintomas;

    public String entidadMedica;

    public String estado;

    public Double presionSanguinea;

    public Double frecuenciaCardiaca;

    public int nivelEstres;

   


    public Paciente(){
        this.id=null;
        this.nombre="NO NAME";
        this.apellido="NO APE";
        this.edad=-1;
        this.sintomas="NO SIN";
        this.entidadMedica= "NO MED";
        this.estado= "NO ESTADO";
        this.presionSanguinea= 0.0;
        this.frecuenciaCardiaca=0.0;
        this.nivelEstres=0;

    }

    public Paciente(Long id){
        this();
        this.id=id;
    }

    public Paciente(String nombre, String apellido, int edad, String sintomas, String entidadMedica, String estado, Double presionSanguinea, Double frecuenciaCardiaca, int nivelEstres) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        
        this.sintomas = sintomas;
        this.entidadMedica = entidadMedica;
        this.estado = estado;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.nivelEstres = nivelEstres;
        this.presionSanguinea = presionSanguinea;

        
     

    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getEntidadMedica() {
        return entidadMedica;
    }

    public void setEntidadMedica(String entidadMedica) {
        this.entidadMedica = entidadMedica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getPresionSanguinea() {
        return presionSanguinea;
    }

    public void setPresionSanguinea(Double presionSanguinea) {
        this.presionSanguinea = presionSanguinea;
    }

    public Double getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Double frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        this.nivelEstres = nivelEstres;
    }

    

    
    





  
    


   

    public static Paciente bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        String apellido = j.findPath("apellido").asText();
        int edad = j.findPath("edad").asInt();
        String sintomas = j.findPath("sintomas").asText();
        String entidadMedica = j.findPath("entidad").asText();
        String estado = j.findPath("estado").asText();
        Double frecuenciaCardiaca = j.findPath("frecuenciaCardiaca").asDouble();
        Double presionSanguinea = j.findPath("presionSanguinea").asDouble();
        int nivelEstres = j.findPath("nivelEstres").asInt();
       

       Paciente paciente = new Paciente(nombre, apellido, edad,sintomas, entidadMedica, estado, presionSanguinea, frecuenciaCardiaca, nivelEstres);
        return paciente;
    }

    public void update(Paciente nuevoPaciente) {
        this.setNombre((nuevoPaciente.getNombre()));
        this.setApellido((nuevoPaciente.getApellido()));
        this.setEdad((nuevoPaciente.getEdad()));
        
        this.setSintomas((nuevoPaciente.getSintomas()));
        this.setEntidadMedica((nuevoPaciente.getEntidadMedica()));
        this.setEstado((nuevoPaciente.getEstado()));
        this.setFrecuenciaCardiaca(nuevoPaciente.getFrecuenciaCardiaca());
        this.setPresionSanguinea(nuevoPaciente.getPresionSanguinea());
        this.setNivelEstres(nuevoPaciente.getNivelEstres());

    }

    public void delete() {
        this.setId(null);
        this.setNombre("");
        this.setApellido("");
        this.setEdad(0);
       
        this.setSintomas("");
        this.setEntidadMedica("");
        this.setEstado("");
    }

}
