package models;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by haes_ on 6/04/2017.
 */
public class Notificacion {

    private Long id;

    private double presionSanguinea;

    private double frecuenciaCardiaca;

    private double nivelDeEstres;

    public Notificacion(){
        this.id=null;
        this.presionSanguinea=-1;
        this.frecuenciaCardiaca=-1;
        this.nivelDeEstres=-1;
    }

    public Notificacion(Long id) {
        this.id = id;
    }

    public Notificacion(double presionSanguinea, double frecuenciaCardiaca, double nivelDeEstres) {
        this.presionSanguinea = presionSanguinea;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.nivelDeEstres = nivelDeEstres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPresionSanguinea() {
        return presionSanguinea;
    }

    public void setPresionSanguinea(double presionSanguinea) {
        this.presionSanguinea = presionSanguinea;
    }

    public double getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(double frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public double getNivelDeEstres() {
        return nivelDeEstres;
    }

    public void setNivelDeEstres(double nivelDeEstres) {
        this.nivelDeEstres = nivelDeEstres;
    }

    public static Notificacion bind(JsonNode j){
        double presion= j.findPath("presionSanguinea").asDouble();
        double frecuencia=j.findPath("frecuenciaSanguinea").asDouble();
        double estres=j.findPath("nivelDeEstres").asDouble();

        Notificacion not= new Notificacion(presion,frecuencia,estres);
        return not;
    }

    public void update(Notificacion nuevaNot){
        this.setFrecuenciaCardiaca(nuevaNot.getFrecuenciaCardiaca());
        this.setNivelDeEstres(nuevaNot.getNivelDeEstres());
        this.setPresionSanguinea(nuevaNot.getNivelDeEstres());
    }

    public void delete(){
        this.setFrecuenciaCardiaca(0.0);
        this.setPresionSanguinea(0.0);
        this.setNivelDeEstres(0.0);
    }
}
