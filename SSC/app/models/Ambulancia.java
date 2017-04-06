package models;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by haes_ on 6/04/2017.
 */
public class Ambulancia {

    private Long id;

    private Paciente paciente;

    private Notificacion notificacion;

    public Ambulancia() {
        this.id = null;
        this.paciente = null;
        this.notificacion = null;
    }

    public Ambulancia(Long id) {
        this.id = id;
    }

    public Ambulancia(Paciente paciente, Notificacion notificacion) {
        this.paciente = paciente;
        this.notificacion = notificacion;
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

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public static Ambulancia bind(JsonNode j) {
        Paciente paciente = new Paciente();
        Notificacion notificacion = new Notificacion();

        paciente.bind(j);
        notificacion.bind(j);

        Ambulancia ambulancia = new Ambulancia(paciente, notificacion);
        return ambulancia;

    }


    public void update(Ambulancia nuevaAmb) {
        this.setPaciente(nuevaAmb.getPaciente());
        this.setNotificacion(nuevaAmb.getNotificacion());
    }

    public void delete(){
        this.setId(null);
        this.setPaciente(null);
        this.setNotificacion(null);
    }
}
