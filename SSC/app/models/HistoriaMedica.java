package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

/**
 * Created by haes_ on 14/02/2017.
 */

@Entity
@Table(name="historial")
public class HistoriaMedica extends Model{

    public static Finder<Long,HistoriaMedica> FINDER = new Finder<>(HistoriaMedica.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historial")
    public Long id;

    private String diagnostico;

    public String tratamiento;

    private String examen;

    private String fecha;
    
    private Paciente paciente;


    public HistoriaMedica(String diagnostico, String tratamiento, String examen, String fecha, Paciente pas) {
        this.diagnostico=diagnostico;
        this.examen=examen;
        this.tratamiento = tratamiento;
        this.fecha = fecha;
        this.paciente = pas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    


    public static HistoriaMedica bind(JsonNode j) {

        String diagnostico= j.findPath("diagnostico").asText();
        String tratamientos = j.findPath("tratamiento").asText();
        String examen =j.findPath("examen").asText();
        String fecha = j.findPath("fecha").asText();
        Paciente paciente = new Paciente();
        paciente.bind(j);
        HistoriaMedica historiaMedica = new HistoriaMedica( diagnostico,tratamientos,examen, fecha,paciente);
        return historiaMedica;
    }

    public void update(HistoriaMedica nuevaHistoria) {
        this.setExamen(nuevaHistoria.getExamen());
        this.setDiagnostico(nuevaHistoria.getDiagnostico());
        this.setTratamiento((nuevaHistoria.getTratamiento()));
        this.setFecha((nuevaHistoria.getFecha()));
    }

    public void delete(){
        this.setExamen("");
        this.setDiagnostico("");
        this.setFecha("");
        this.setTratamiento("");
    }
}
