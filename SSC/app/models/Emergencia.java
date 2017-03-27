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

public class Emergencia extends Model {


    public Long id;

    public String ubicacion;

    public String tipo;


    public Emergencia() {
    }

    public Emergencia(String ubicacion, String tipo) {

        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public static Emergencia bind(JsonNode j) {
        String ubicacion = j.findPath("ubicacion").asText();
        String tipo = j.findPath("tipo").asText();
        Emergencia emergencia = new Emergencia(ubicacion, tipo);
        return emergencia;
    }

}

