

package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Hospital;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class HospitalController extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Hospital hos = Hospital.bind(j);
        hos.save();
        return ok(Json.toJson(hos));
    }

    public Result read() {
        List<Hospital> hospitales = new Model.Finder(String.class, Hospital.class).all();
        return ok(Json.toJson(hospitales));
    }

    

    public Result getId(Long id) {
        Hospital hospital = (Hospital) new Model.Finder(String.class, Hospital.class).byId(id);
        ObjectNode result = Json.newObject();
        if (hospital == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(hospital));
        }
    }
}
    
    
    

