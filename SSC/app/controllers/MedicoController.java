package controllers;

/**
 * Created by haes_ on 14/02/2017.
 */

import akka.dispatch.MessageDispatcher;
import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Medico;
import models.Notificacion;
import models.Paciente;
import models.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.CompletionStage;

public class MedicoController extends Controller {


    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Medico medico = Medico.bind(j);
        medico.save();
        return ok(Json.toJson(medico));
    }

    public Result read() {
        List<Medico> medicos = new Model.Finder(String.class, Medico.class).all();
        return ok(Json.toJson(medicos));
    }

    public Result get(Long id) {
        Medico medico = (Medico) new Model.Finder(Long.class, Medico.class).byId(id);
        ObjectNode result = Json.newObject();
        if (medico == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(medico));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Medico medicoViejo = (Medico) new Model.Finder(Long.class, Medico.class).byId(id);
        ObjectNode result = Json.newObject();
        if (medicoViejo == null)
            return ok(Json.toJson(result));
        else {
            Medico medicoNuevo;
            medicoNuevo = Medico.bind(j);
            medicoViejo.update(medicoNuevo);
            medicoViejo.save();
            return ok(Json.toJson(medicoViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Medico medico = (Medico) new Model.Finder(Long.class, Medico.class).byId(id);
        if (medico.getId() == id) {
            medico.delete();
            medico.save();
        }
        return ok(Json.toJson(medico));
    }
    public Result getNotificacion(int idMedico, int idNotificacion)
    {

        Medico medico = (Medico) new Model.Finder(Long.class, Medico.class).byId(idMedico);
        if(medico==null){
            return ok(Json.toJson(result));
        }
        List<Notificacion> notificaciones= medico.getNotificaciones();
        for(int i=0; i<notificaciones.size(); i++){
            Notificacion n = notificaciones.get(i);
            if(n.getId()== idNotificacion){
                return ok(Json.toJson(n));
            }
        }
        return ok(Json.toJson(result));
    }
    public Result getSensor(int idMedico, int idPaciente)
    {

        Medico medico = (Medico) new Model.Finder(Long.class, Medico.class).byId(idMedico);
        if(medico==null){
            return ok(Json.toJson(result));
        }
        Paciente paciente = (Paciente) new Model.Finder(Long.class, Medico.class).byId(idPaciente);

        if (paciente==null){
            return ok(Json.toJson(result));
        }
            Sensor n = paciente.getSensor();
            if(n.getId()== idNotificacion){
                return ok(Json.toJson(n));

        }
        return ok(Json.toJson(result));
    }


}
