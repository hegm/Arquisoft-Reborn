package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicoentity")
public class Medico extends Model {

    public static Finder<Long,Medico> FINDER = new Finder<>(Medico.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;

    private String nombre;
    private int edad;
    private String especializacion;
    private List<Paciente> pacientes;
    private List<Notificacion> notificaciones;

    public Medico() {

        this.id =null;
        this.nombre= "NO NAME";
        this.edad= -1;
        this.especializacion= " NO ESP";

    }

    public Medico(Long id){
        this();
        this.id=id;
    }

    public Medico(String nombre, int edad, String especializacion, List<Paciente> pacientes, List<Notificacion> notificaciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.especializacion = especializacion;
        this.pacientes = pacientes;
        this.notificaciones= notificaciones;
        
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    public static Medico bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        int edad = j.findPath("edad").asInt();
        String especializacion = j.findPath("especializacion").asText();
        List pacientes = j.findValuesAsText("pacientes");
        List notificaciones= j.findValuesAsText("notificaciones");

        Medico medico = new Medico(nombre, edad, especializacion, pacientes,notificaciones);
        return medico;
    }


    public void update(Medico nuevoMedico) {
        this.setNombre((nuevoMedico.getNombre()));
        this.setEdad((nuevoMedico.getEdad()));
        this.setEspecializacion((nuevoMedico.getEspecializacion()));
        this.setPacientes(nuevoMedico.getPacientes());
        this.setNotificaciones(nuevoMedico.getNotificaciones());

    }

    public void delete() {
        this.setId(null);
        this.setNombre(" ");
        this.setEdad(0);
        this.setEspecializacion(" ");
        this.setPacientes(null);
        this.setNotificaciones(null);


    }
    
    
   
            
          
    
    
}


