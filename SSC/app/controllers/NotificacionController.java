package controllers;

<<<<<<< HEAD
import models.Notificacion;
=======
import com.avaje.ebean.Model;
>>>>>>> origin/master

/**
 * Created by haes_ on 6/04/2017.
 */
<<<<<<< HEAD
public class NotificacionController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Notificacion notificacion = Notificacion.bind(j);
        notificacion.save();
        return ok(Json.toJson(notificacion));
    }

    public Result read() {
        List<Notificacion> notificacions = new Model.Finder(String.class, Notificacion.class).all();
        return ok(Json.toJson(notificacions));
    }

    public Result get(Long id) {
        Notificacion notificacion = (Notificacion) new Model.Finder(Long.class, Notificacion.class).byId(id);
        ObjectNode result = Json.newObject();
        if (notificacion == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(notificacion));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Notificacion notificacionViejo = (Notificacion) new Model.Finder(Long.class, Notificacion.class).byId(id);
        ObjectNode result = Json.newObject();
        if (notificacionViejo == null)
            return ok(Json.toJson(result));
        else {
            Notificacion notificacionNuevo;
            notificacionNuevo = Notificacion.bind(j);
            notificacionViejo.update(notificacionNuevo);
            notificacionViejo.save();
            return ok(Json.toJson(notificacionViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Notificacion notificacion = (Notificacion) new Model.Finder(Long.class, Notificacion.class).byId(id);
        if (notificacion.getId() == id) {
            notificacion.delete();
            notificacion.save();
        }
        return ok(Json.toJson(notificacion));
    }
=======

public class NotificacionController extends Model {




>>>>>>> origin/master
}
