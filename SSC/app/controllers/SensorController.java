package controllers;

import models.Sensor;

import java.util.List;

/**
 * Created by haes_ on 6/04/2017.
 */
public class SensorController {
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {
        JsonNode j = Controller.request().body().asJson();
        Sensor sensor = Sensor.bind(j);
        sensor.save();
        return ok(Json.toJson(sensor));
    }

    public Result read() {
        List<Sensor> sensors = new Model.Finder(String.class, Sensor.class).all();
        return ok(Json.toJson(sensors));
    }

    public Result get(Long id) {
        Sensor sensor = (Sensor) new Model.Finder(Long.class, Sensor.class).byId(id);
        ObjectNode result = Json.newObject();
        if (sensor == null)
            return ok(Json.toJson(result));
        else {
            return ok(Json.toJson(sensor));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {
        JsonNode j = Controller.request().body().asJson();
        Sensor sensorViejo = (Sensor) new Model.Finder(Long.class, Sensor.class).byId(id);
        ObjectNode result = Json.newObject();
        if (sensorViejo == null)
            return ok(Json.toJson(result));
        else {
            Sensor sensorNuevo;
            sensorNuevo = Sensor.bind(j);
            sensorViejo.update(sensorNuevo);
            sensorViejo.save();
            return ok(Json.toJson(sensorViejo));
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result delete(Long id) {
        Sensor sensor = (Sensor) new Model.Finder(Long.class, Sensor.class).byId(id);
        if (sensor.getId() == id) {
            sensor.delete();
            sensor.save();
        }
        return ok(Json.toJson(sensor));
    }
}
