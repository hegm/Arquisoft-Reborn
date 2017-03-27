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
    private Hospital hospital;

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }


    public OperarioEmergencia() {

    }

    public OperarioEmergencia(String notificacion, Hospital hospital) {
        this.notificacion = notificacion;
        this.hospital = hospital;
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
       Hospital hospital = new Hospital();
       Hospital.bind(j);
       OperarioEmergencia operario = new OperarioEmergencia();
       

        return operario;
    }

    public void update(OperarioEmergencia NuevoOperario) {
        this.setNotificacion(NuevoOperario.getNotificacion());
    }
}
