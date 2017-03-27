package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.OperarioEmergencia;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by haes_ on 4/03/2017.
 */
public class OperarioController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        OperarioEmergencia op = OperarioEmergencia.bind(j);
        op.save();
        return ok(Json.toJson(op));
    }

    public Result read() {
        List<OperarioEmergencia> operarios = new Model.Finder(String.class, OperarioEmergencia.class).all();
        return ok(Json.toJson(operarios));
    }

    public Result getId(Long id) {
        OperarioEmergencia op = (OperarioEmergencia) new Model.Finder(String.class, OperarioEmergencia.class).byId(id);
        ObjectNode result = Json.newObject();
        if (op == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(op));
        }
    }
}
