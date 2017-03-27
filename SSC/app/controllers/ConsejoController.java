package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Consejo;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by estebandalelr on 13/03/2017.
 */
public class ConsejoController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        //system.out.println("Begin create Consejo");
        JsonNode j = Controller.request().body().asJson();
        Consejo consejo = Consejo.bind(j);
        consejo.save();
        //System.out.println("Finish Create Consejo");
        return ok(Json.toJson(consejo));
    }

    public Result read() {
       // System.out.println("Begin reading Consejo");
        List<Consejo> consejos = new Model.Finder(String.class, Consejo.class).all();
       // System.out.println("Finish reading Consejo");
        return ok(Json.toJson(consejos));
    }

    public Result get(Long id) {
        Consejo consejo = (Consejo) new Model.Finder(Long.class, Consejo.class).byId(id);
        ObjectNode result = Json.newObject();
        if (consejo == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(consejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Consejo consejoVieja = (Consejo) new Model.Finder(Long.class, Consejo.class).byId(id);
        ObjectNode result = Json.newObject();
        if (consejoVieja == null)
            return ok(Json.toJson(result));
        else {
            Consejo consejoNueva;
            consejoNueva = Consejo.bind(j);
            consejoVieja.update(consejoNueva);
            consejoVieja.save();
            return ok(Json.toJson(consejoVieja));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Consejo consejo = (Consejo) new Model.Finder(Long.class, Consejo.class).byId(id);
        if (consejo.getId() ==id) {
            consejo.delete();
            consejo.save();
        }

        return ok(Json.toJson(consejo));
    }
}
