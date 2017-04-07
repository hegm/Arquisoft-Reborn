package controllers;

import models.Ambulancia;

import java.util.List;

/**
 * Created by haes_ on 6/04/2017.
 */
public class AmbulanciaController {
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
