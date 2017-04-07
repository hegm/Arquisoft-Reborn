
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



    public int edad;

    public Sensor sensor;


    private List<Consejo> consejos;

    private Medico medico;

    private List<Notificacion> notificaciones;

   


    public Paciente(){
        this.id=null;
        this.nombre="NO NAME";
        this.edad=-1;
        this.sensor= new Sensor();
        this.notificaciones= null;
        this.medico=new Medico();

    }

    public Paciente(Long id){
        this();
        this.id=id;
    }

                    public Paciente(String nombre,  int edad, String sintomas, Medico medico, Sensor sensor, List<Notificacion> notificaciones) {
        this.nombre = nombre;

        this.edad = edad;
        this.medico=medico;
        
        this.sensor = sensor;
        this.notificaciones = notificaciones;



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

    
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String entidadMedica) {
        this.medico = entidadMedica;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

<<<<<<< HEAD
=======

>>>>>>> origin/master
    public static Paciente bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        int edad = j.findPath("edad").asInt();
        List notificaciones= j.findValuesAsText("notificaciones");


       Paciente paciente = new Paciente(nombre, edad, this.medico.bind(j), this.sensor.bind(j), notificaciones);
        return paciente;
    }

    public void update(Paciente nuevoPaciente) {
        this.setNombre((nuevoPaciente.getNombre()));
        this.setMedico((nuevoPaciente.getMedico()));
        this.setEdad((nuevoPaciente.getEdad()));
        
        this.setSensor((nuevoPaciente.getSensor()));
        this.setNotificaciones((nuevoPaciente.getNotificaciones()));


    }

    public void delete() {
        this.setId(null);
        this.setNombre("");
        this.setMedico(null);
        this.setEdad(0);
       
        this.setNotificaciones(null);
        this.setSensor(null);
       
    }

}
