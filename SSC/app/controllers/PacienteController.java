package controllers;

/**
 * Created by haes_ on 14/02/2017.
 */

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Paciente;
import models.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import models.Consejo;


public class PacienteController extends Controller {


    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Paciente paciente = Paciente.bind(j);
        paciente.save();
        return ok(Json.toJson(paciente));
    }


    public Result read() {
        List<Paciente> pacientes = new Model.Finder(String.class, Paciente.class).all();
        return ok(Json.toJson(pacientes));
    }

    public Result get(Long id) {
        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(id);
        ObjectNode result = Json.newObject();
        if (paciente == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(paciente));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Paciente pacienteViejo = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(id);
        ObjectNode result = Json.newObject();
        if (pacienteViejo == null)
            return ok(Json.toJson(result));
        else {
            Paciente pacienteNuevo;
            pacienteNuevo = Paciente.bind(j);
            pacienteViejo.update(pacienteNuevo);
            pacienteViejo.save();
            return ok(Json.toJson(pacienteViejo));
        }
    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(id);
        if (paciente.getId() == id) {
            paciente.delete();
            paciente.save();
        }

        return ok(Json.toJson(paciente));
    }

    public Result getSensor(Long idPaciente, Long idSensor)
    {

        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(idPaciente);
        ObjectNode result = Json.newObject();
        if(paciente==null){
            return ok(Json.toJson(result));
        }

        Sensor n = paciente.getSensor();
        if(n.getId()== idSensor){
            return ok(Json.toJson(n));

        }
        return ok(Json.toJson(result));
    }
    
    public Result getConsejo(Long idPaciente, Long idConsejo)
    {

        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(idPaciente);
        ObjectNode result = Json.newObject();
        if(paciente==null){
            return ok(Json.toJson(result));
        }

     Consejo consejos = paciente.getConsejos();

            Consejo con = consejos;
            
            boolean ok = integridad(con);
            
            
            if(ok){
               return ok(Json.toJson(consejos));        
            }  
            else {
                return ok(Json.toJson(result));
            }
            

    }
    
    
    public boolean integridad(Consejo con)
    {
        boolean integro = false;
        String x = con.getMedicamento();
        int y = con.CONS;
        Long h = con.getHash();
        
        Long hash = con.Hash(x, y);
        
        if(hash == h){
            integro = true;
        }
        
        
        return integro;
    }

}
