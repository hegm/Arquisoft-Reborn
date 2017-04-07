package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by haes_ on 6/04/2017.
 */

@Entity
@Table(name = "sensorentity")
public class Sensor extends Model {

    public static Finder<Long,Sensor> FINDER = new Finder<>(Sensor.class);

    public final static int FRECUENCIA_CARDIACA=0;
    public final static int PRESION_SANGUINEA=1;
    public final static int ESTRES=2;
    public final static int GPS=3;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sensor")

    private Long id;


    private String nombre;


    private int tipo;


    private double valor;


    private Paciente paciente;

    public Sensor() {

        this.id =null;
        this.nombre= "NO NAME";
        this.tipo= -1;
        this.valor= -1.0;
        this.paciente= null;

    }

    public Sensor (Long id){
        this();
        this.id=id;
    }

    public Sensor (String nombre,int tipo,double valor, Paciente paciente) {
        this.nombre= nombre;
        this.tipo= tipo;
        this.valor= valor;
        this.paciente= paciente;

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }



    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public static Sensor bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        int tipo = j.findPath("tipo").asInt();
        double valor= j.findPath("valor").asDouble();
        Paciente paciente=new Paciente();
        paciente.bind(j);

        Sensor sensor = new Sensor(nombre,tipo, valor,paciente);
        return sensor;
    }


    public void update(Sensor nuevoSensor) {
        this.setNombre((nuevoSensor.getNombre()));
        this.setTipo((nuevoSensor.getTipo()));
        this.setValor((nuevoSensor.getValor()));
        this.setPaciente(nuevoSensor.getPaciente());


    }

    public void delete() {
        this.setId(null);
        this.setNombre(" ");
        this.setTipo(0);
        this.setValor(0.0);
        this.setPaciente(null);
    }

}
