package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.HistoriaMedica;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by estebandalelr on 13/03/2017.
 */
public class HistoriaMedicaController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        //system.out.println("Begin create HistoriaMedica");
        JsonNode j = Controller.request().body().asJson();
        HistoriaMedica historiaMedica = HistoriaMedica.bind(j);
        historiaMedica.save();
        //System.out.println("Finish Create HistoriaMedica");
        return ok(Json.toJson(historiaMedica));
    }

    public Result read() {
        // System.out.println("Begin reading HistoriaMedica");
        List<HistoriaMedica> historiaMedicas = new Model.Finder(String.class, HistoriaMedica.class).all();
        // System.out.println("Finish reading HistoriaMedica");
        return ok(Json.toJson(historiaMedicas));
    }

    public Result get(Long id) {
        HistoriaMedica historiaMedica = (HistoriaMedica) new Model.Finder(Long.class, HistoriaMedica.class).byId(id);
        ObjectNode result = Json.newObject();
        if (historiaMedica == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(historiaMedica));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        HistoriaMedica historiaMedicaVieja = (HistoriaMedica) new Model.Finder(Long.class, HistoriaMedica.class).byId(id);
        ObjectNode result = Json.newObject();
        if (historiaMedicaVieja == null)
            return ok(Json.toJson(result));
        else {
            HistoriaMedica historiaMedicaNueva;
            historiaMedicaNueva = HistoriaMedica.bind(j);
            historiaMedicaVieja.update(historiaMedicaNueva);
            historiaMedicaVieja.save();
            return ok(Json.toJson(historiaMedicaVieja));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        HistoriaMedica historiaMedica = (HistoriaMedica) new Model.Finder(Long.class, HistoriaMedica.class).byId(id);
        if (historiaMedica.getId() ==id) {
            historiaMedica.delete();
            historiaMedica.save();
        }

        return ok(Json.toJson(historiaMedica));
    }
}
