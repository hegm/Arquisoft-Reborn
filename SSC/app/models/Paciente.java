
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import state.EstadoAmarillo;
import state.EstadoRojo;
import state.EstadoVerde;
import state.IEstadoPaciente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import mediator.AlertMediator;

@Entity
@Table(name = "pacienteentity")
public class Paciente extends Model {

    
    public AlertMediator mediator;

    public static Finder<Long,Paciente> FINDER = new Finder<>(Paciente.class);
    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    private String nombre;

    private IEstadoPaciente sano;
    private IEstadoPaciente alerta;
    private IEstadoPaciente urgente;

    private IEstadoPaciente estado;

    private int edad;

    private Sensor sensor;


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
        sano= new EstadoVerde(this);
        alerta= new EstadoAmarillo(this);
        urgente = new EstadoRojo(this);

        estado= sano;

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


    public void setEstadoPaciente(IEstadoPaciente nuevoEstado){

        estado=nuevoEstado;
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


    public IEstadoPaciente getEstadoVerde(){return sano;}
    public IEstadoPaciente getEstadoAmarillo(){return alerta;}
    public IEstadoPaciente getEstadoRojo(){return urgente;}

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
    
    public void registarViejas(){
        
        for(int i = 0; i < notificaciones.size(); i++){
            Notificacion nueva = notificaciones.get(i);
            mediator.Register(nueva);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
    

}
