package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by haes_ on 14/02/2017.
 */
public class HistoriaMedica extends Model{

    public Long id;
    

    
    public String eventos;

    public String tratamientos;

    public String fecha;
    
    public Paciente paciente;


    public HistoriaMedica(String eventos, String tratamientos, String fecha,Paciente pas) {
        this.eventos = eventos;
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.paciente = pas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getEventos() {
        return eventos;
    }

    public void setEventos(String eventos) {
        this.eventos = eventos;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
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

        String eventos = j.findPath("eventos").asText();
        String tratamientos = j.findPath("tratamientos").asText();
        String fecha = j.findPath("fecha").asText();
        Paciente paciente = new Paciente();
        paciente.bind(j);
        HistoriaMedica historiaMedica = new HistoriaMedica(eventos, tratamientos, fecha,paciente);
        return historiaMedica;
    }

    public void update(HistoriaMedica nuevaHistoria) {
        this.setEventos((nuevaHistoria.getEventos()));
        this.setTratamientos((nuevaHistoria.getTratamientos()));
        this.setFecha((nuevaHistoria.getFecha()));
    }
}
