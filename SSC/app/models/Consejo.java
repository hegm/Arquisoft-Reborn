/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

/**
 *
 * @author yf.rivera1851
 */

@Entity
@Table(name="consejo")
public class Consejo extends Model {
    public static Finder<Long,Consejo> FINDER = new Finder<>(Consejo.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Consejo")

    private Long id;
    private String dieta;
    private String rutina;
    private String medicamento;
    private String cita;


    
    public Consejo()
    {
        this.id=null;
        this.dieta="NO NAME";
        this.rutina="NO NAME";
        this.medicamento="NO NAME";
        this.cita="NO NAME";
    }

    public Consejo(Long id){
        this();
        this.id=id;
    }

    public Consejo(String dieta, String rutina, String medicamento, String cita) {

        this.dieta = dieta;
        this.rutina = rutina;
        this.medicamento = medicamento;
        this.cita = cita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getRutina() {
        return rutina;
    }

    public void setRutina(String rutina) {
        this.rutina = rutina;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public static Consejo bind(JsonNode j) {
        String dieta = j.findPath("dieta").asText();
        String rutina = j.findPath("rutina").asText();
        String medicamento = j.findPath("medicamento").asText();
        String cita= j.findPath("medicamento").asText();

        Consejo consejo = new Consejo(dieta,rutina,medicamento,cita);
        return consejo;
    }

    public void update(Consejo nuevoConsejo) {
       this.setCita(nuevoConsejo.getCita());
       this.setDieta(nuevoConsejo.getDieta());
       this.setMedicamento(nuevoConsejo.getMedicamento());
       this.setRutina(nuevoConsejo.getRutina());
    }

    public void delete() {
        this.setId(null);
        this.setRutina("");
        this.setMedicamento("");
        this.setDieta("");
        this.setCita("");
    }
    
    
    
    
    
}
