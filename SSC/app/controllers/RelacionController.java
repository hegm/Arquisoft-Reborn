package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Relacion;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by estebandalelr on 13/03/2017.
 */
public class RelacionController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        //system.out.println("Begin create Relacion");
        JsonNode j = Controller.request().body().asJson();
        Relacion relacion = Relacion.bind(j);
        relacion.save();
        //System.out.println("Finish Create Relacion");
        return ok(Json.toJson(relacion));
    }

    public Result read() {
        // System.out.println("Begin reading Relacion");
        List<Relacion> relacions = new Model.Finder(String.class, Relacion.class).all();
        // System.out.println("Finish reading Relacion");
        return ok(Json.toJson(relacions));
    }

    public Result get(Long id) {
        Relacion relacion = (Relacion) new Model.Finder(Long.class, Relacion.class).byId(id);
        ObjectNode result = Json.newObject();
        if (relacion == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(relacion));
        }
    }

    

}
