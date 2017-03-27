package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Cita;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by haes_ on 14/02/2017.
 */
public class CitaController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        System.out.println("Begin create Cita");
        JsonNode j = Controller.request().body().asJson();
        Cita cita = Cita.bind(j);
        cita.save();
        System.out.println("Finish Create Cita");
        return ok(Json.toJson(cita));
    }

    public Result read() {
        System.out.println("Begin reading Cita");
        List<Cita> citas = new Model.Finder(String.class, Cita.class).all();
        System.out.println("Finish reading Cita");
        return ok(Json.toJson(citas));
    }

    public Result get(Long id) {
        Cita cita = (Cita) new Model.Finder(Long.class, Cita.class).byId(id);
        ObjectNode result = Json.newObject();
        if (cita == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(cita));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Cita citaVieja = (Cita) new Model.Finder(Long.class, Cita.class).byId(id);
        ObjectNode result = Json.newObject();
        if (citaVieja == null)
            return ok(Json.toJson(result));
        else {
            Cita citaNueva;
            citaNueva = Cita.bind(j);
            citaVieja.update(citaNueva);
            citaVieja.save();
            return ok(Json.toJson(citaVieja));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Cita cita = (Cita) new Model.Finder(Long.class, Cita.class).byId(id);
        if (cita.getId() ==id) {
            cita.delete();
            cita.save();
        }

        return ok(Json.toJson(cita));
    }
}
