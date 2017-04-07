package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Ambulancia;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;


import java.util.List;

/**
 * Created by haes_ on 6/04/2017.
 */
public class AmbulanciaController extends Controller {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Ambulancia ambulancia = Ambulancia.bind(j);
        ambulancia.save();
        return ok(Json.toJson(ambulancia));
    }

    public Result read() {
        List<Ambulancia> ambulancias = new Model.Finder(String.class, Ambulancia.class).all();
        return ok(Json.toJson(ambulancias));
    }

    public Result get(Long id) {
        Ambulancia ambulancia = (Ambulancia) new Model.Finder(Long.class, Ambulancia.class).byId(id);
        ObjectNode result = Json.newObject();
        if (ambulancia == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(ambulancia));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Ambulancia ambulanciaViejo = (Ambulancia) new Model.Finder(Long.class, Ambulancia.class).byId(id);
        ObjectNode result = Json.newObject();
        if (ambulanciaViejo == null)
            return ok(Json.toJson(result));
        else {
            Ambulancia ambulanciaNuevo;
            ambulanciaNuevo = Ambulancia.bind(j);
            ambulanciaViejo.update(ambulanciaNuevo);
            ambulanciaViejo.save();
            return ok(Json.toJson(ambulanciaViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Ambulancia ambulancia = (Ambulancia) new Model.Finder(Long.class, Ambulancia.class).byId(id);
        if (ambulancia.getId() == id) {
            ambulancia.delete();
            ambulancia.save();
        }
        return ok(Json.toJson(ambulancia));
    }
}
