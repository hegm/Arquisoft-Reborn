package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicoentity")
public class Medico extends Model {

    public static Finder<Long,Medico> FINDER = new Finder<>(Medico.class);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Medico")
    public Long id;

    public String nombre;
    public int edad;
    public String especializacion;
    

    public Medico() {

        this.id =null;
        this.nombre= "NO NAME";
        this.edad= -1;
        this.especializacion= " NO ESP";

    }

    public Medico(Long id){
        this();
        this.id=id;
    }

    public Medico(String nombre, int edad, String especializacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.especializacion = especializacion;
        
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }


    

    public static Medico bind(JsonNode j) {
        String nombre = j.findPath("nombre").asText();
        int edad = j.findPath("edad").asInt();
        String especializacion = j.findPath("especializacion").asText();
        

        Medico medico = new Medico(nombre, edad, especializacion);
        return medico;
    }


    public void update(Medico nuevoMedico) {
        this.setNombre((nuevoMedico.getNombre()));
        this.setEdad((nuevoMedico.getEdad()));
        this.setEspecializacion((nuevoMedico.getEspecializacion()));

    }

    public void delete() {
        this.setId(null);
        this.setNombre(" ");
        this.setEdad(0);
        this.setEspecializacion(" ");

    }
}


