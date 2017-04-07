package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Notificacion;

import com.avaje.ebean.Model;
import models.Paciente;
import models.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;


/**
 * Created by haes_ on 6/04/2017.
 */

public class NotificacionController extends Controller {
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

    public Result getFrecuencia(int idPaciente)
    {
        double frecuencia=10;

       Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(idPaciente);
        ObjectNode result = Json.newObject();
        if(paciente==null){
            return ok(Json.toJson(result));
        }
        Sensor sensor= paciente.getSensor();

        List<Notificacion> notificaciones= paciente.getNotificaciones();
        for(int i=0; i<notificaciones.size(); i++){
            Notificacion n = notificaciones.get(i);
            if(sensor.getTipo()==0){
                n.setFrecuenciaCardiaca(sensor.getValor());
                frecuencia=n.getFrecuenciaCardiaca();
                return ok(Json.toJson(frecuencia));
            }
        }
        return ok(Json.toJson(result));
    }

    public Result getPresion(int idPaciente)
    {
        double presion=0;

        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(idPaciente);
        ObjectNode result = Json.newObject();
        if(paciente==null){
            return ok(Json.toJson(result));
        }
        Sensor sensor= paciente.getSensor();

        List<Notificacion> notificaciones= paciente.getNotificaciones();
        for(int i=0; i<notificaciones.size(); i++){
            Notificacion n = notificaciones.get(i);
            if(sensor.getTipo()==1){
                n.setFrecuenciaCardiaca(sensor.getValor());
                presion=n.getFrecuenciaCardiaca();
                return ok(Json.toJson(presion));
            }
        }
        return ok(Json.toJson(result));
    }

    public Result getEstres(int idPaciente)
    {
        double estres=0;

        Paciente paciente = (Paciente) new Model.Finder(Long.class, Paciente.class).byId(idPaciente);
        ObjectNode result = Json.newObject();
        if(paciente==null){
            return ok(Json.toJson(result));
        }
        Sensor sensor= paciente.getSensor();

        List<Notificacion> notificaciones= paciente.getNotificaciones();
        for(int i=0; i<notificaciones.size(); i++){
            Notificacion n = notificaciones.get(i);
            if(sensor.getTipo()==2){
                n.setFrecuenciaCardiaca(sensor.getValor());
                estres=n.getFrecuenciaCardiaca();
                return ok(Json.toJson(estres));
            }
        }
        return ok(Json.toJson(result));
    }
}
