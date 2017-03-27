package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Emergencia;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by estebandalelr on 13/03/2017.
 */
public class EmergenciaController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        //system.out.println("Begin create Emergencia");
        JsonNode j = Controller.request().body().asJson();
        Emergencia emergencia = Emergencia.bind(j);
        emergencia.save();
        //System.out.println("Finish Create Emergencia");
        return ok(Json.toJson(emergencia));
    }

    public Result read() {
        // System.out.println("Begin reading Emergencia");
        List<Emergencia> emergencias = new Model.Finder(String.class, Emergencia.class).all();
        // System.out.println("Finish reading Emergencia");
        return ok(Json.toJson(emergencias));
    }

    public Result get(Long id) {
        Emergencia emergencia = (Emergencia) new Model.Finder(Long.class, Emergencia.class).byId(id);
        ObjectNode result = Json.newObject();
        if (emergencia == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(emergencia));
        }
    }

   
    public Result delete(Long id) {
        Emergencia emergencia = (Emergencia) new Model.Finder(Long.class, Emergencia.class).byId(id);
        if (emergencia.getId() ==id) {
            emergencia.delete();
            emergencia.save();
        }

        return ok(Json.toJson(emergencia));
    }
}
