package state;


import models.Notificacion;
import models.Paciente;

import java.util.List;

/**
 * Created by haes_ on 23/04/2017.
 */
public class EstadoRojo implements IEstadoPaciente {

    Paciente paciente;

    public EstadoRojo(Paciente nuevoPaciente){paciente=nuevoPaciente;}

    public void estadoVerde(){
        System.out.println("El paciente se encuentra en estado rojo");
    }

    public void estadoAmarillo() {

        System.out.println("El paciente se encuentra en estado rojo");
    }

    public void estadoRojo(){
        List notificaciones = paciente.getNotificaciones();
        for (int i = 0; i < notificaciones.size(); i++) {
            Notificacion notificacion = paciente.getNotificaciones().get(i);
            double frecuencia = notificacion.getFrecuenciaCardiaca();
            double presion = notificacion.getPresionSanguinea();
            if (frecuencia < 60 || frecuencia > 100 && presion > 139) {
                paciente.setEstadoPaciente(paciente.getEstadoRojo());
                System.out.println("El paciente esta en peligro!");
            } else {
                System.out.println("El paciente no se encuentra en un estado rojo");
            }
        }
    }

}
