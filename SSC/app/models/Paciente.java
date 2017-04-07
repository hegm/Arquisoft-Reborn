
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
        this.sensor= null;
        this.notificaciones= null;
        this.medico=null;

    }

    public Paciente(Long id){
        this();
        this.id=id;
    }

     public Paciente(String nombre,  int edad,  Medico medico, Sensor sensor, List<Notificacion> notificaciones) {
        this.nombre = nombre;

        this.edad = edad;
        this.medico=medico;
        
        this.sensor = sensor;
        this.notificaciones = notificaciones;



    }

    public List<Consejo> getConsejos() {
        return consejos;
    }

    public void setConsejos(List<Consejo> consejos) {
        this.consejos = consejos;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico entidadMedica) {
        this.medico = entidadMedica;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }


    public static Paciente bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        int edad = j.findPath("edad").asInt();
        List notificaciones= j.findValuesAsText("notificaciones");
        List consejos = j.findValues("consejos");
        Medico medico= new Medico();
        medico.bind(j);
        Sensor sensor = new Sensor();
        sensor.bind(j);

       Paciente paciente = new Paciente(nombre,edad, medico,sensor,notificaciones);
        return paciente;
    }

    public void update(Paciente nuevoPaciente) {
        this.setNombre((nuevoPaciente.getNombre()));
        this.setMedico((nuevoPaciente.getMedico()));
        this.setEdad((nuevoPaciente.getEdad()));
        this.setConsejos(nuevoPaciente.getConsejos());
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
