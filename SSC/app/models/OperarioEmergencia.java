package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by haes_ on 4/03/2017.
 */

@Entity
@Table(name="operario")
public class OperarioEmergencia extends Model {

    public static Finder<Long,OperarioEmergencia> FINDER = new Finder<>(OperarioEmergencia.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OperarioEmergencia")
    private Long id;
    private Paciente paciente;
    private List<Paciente> pacientes;

    public OperarioEmergencia(){
        this.id=null;
        this.paciente=null;
        this.pacientes=null;
    }

    public OperarioEmergencia(Long id) {
        this.id = id;
    }

    public OperarioEmergencia(Paciente paciente, List<Paciente> pacientes) {
        this.paciente = paciente;
        this.pacientes = pacientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public OperarioEmergencia bind(JsonNode j){
        List pacientes = j.findValuesAsText("paciente");
        Paciente paciente= new Paciente();
        paciente.bind(j);

        OperarioEmergencia operarioEmergencia= new OperarioEmergencia(paciente, pacientes);
        return operarioEmergencia;
    }

    public void update(OperarioEmergencia nuevoOP){
        this.setPaciente(nuevoOP.getPaciente());
        this.setPacientes(nuevoOP.getPacientes());
    }

    public void delete(){
        this.setId(null);
        this.setPacientes(null);
        this.setPaciente(null);
    }
}