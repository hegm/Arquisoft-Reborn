package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by haes_ on 4/03/2017.
 */

public class OperarioEmergencia extends Model {


    private Long id;
    private String notificacion;

    public OperarioEmergencia() {

    }

    public OperarioEmergencia(String notificacion) {
        this.notificacion = notificacion;

    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public static OperarioEmergencia bind(JsonNode j) {
        String notificacion = j.findPath("notificacion").asText();
        OperarioEmergencia operario = new OperarioEmergencia();
       

        return operario;
    }

    public void update(OperarioEmergencia NuevoOperario) {
        this.setNotificacion(NuevoOperario.getNotificacion());
    }
}
