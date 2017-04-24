package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;
import java.util.List;
import mediator.AlertMediator;

/**
 * Created by haes_ on 4/03/2017.
 */

@Entity
@Table(name="operarioentity")
public class OperarioEmergencia extends Model {

    public static Finder<Long,OperarioEmergencia> FINDER = new Finder<>(OperarioEmergencia.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Paciente paciente;
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    public AlertMediator mediador;
    
    

    public OperarioEmergencia(){
        this.id=null;
        this.paciente=null;
        this.pacientes=null;
        this.medicos=null;
    }

    public OperarioEmergencia(Long id) {
        this.id = id;
    }

    public OperarioEmergencia(Paciente paciente, List<Paciente> pacientes,List<Medico> medicos) {
        this.paciente = paciente;
        this.pacientes = pacientes;
        this.medicos = medicos;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
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

    public static OperarioEmergencia bind(JsonNode j){
        List pacientes = j.findValuesAsText("paciente");
        List medicos = j.findValuesAsText("medico");
        Paciente paciente= new Paciente();
        paciente.bind(j);

        OperarioEmergencia operarioEmergencia= new OperarioEmergencia(paciente, pacientes, medicos);
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
    
    public void notificarEmergencia(Notificacion n , Medico m,Paciente p)
    {
      m.Alerta(n,p.id);
      mediador.notify(p, m, n);
    }
}