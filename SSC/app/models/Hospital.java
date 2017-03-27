/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;



public class Hospital extends Model {


    public Long id;
    public String nombre;

    
public Hospital() {
        
        
    }


    public Hospital(String nombre) {
        this.nombre = nombre;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

    

       
    public static Hospital bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        
       Hospital hospital=new Hospital(nombre);

        return hospital;
    }

}
