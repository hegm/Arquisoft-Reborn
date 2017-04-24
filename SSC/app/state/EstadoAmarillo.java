package state;

import models.Notificacion;
import models.Paciente;

import java.util.List;

/**
 * Created by haes_ on 23/04/2017.
 */
public class EstadoAmarillo implements IEstadoPaciente {

    Paciente paciente;

        public EstadoAmarillo(Paciente nuevoPaciente)
        {
            paciente=nuevoPaciente;
        }

        public void estadoVerde(){
            System.out.println("El paciente se encuentra en estado amarillo");
        }

        public void estadoAmarillo() {

            List notificaciones = paciente.getNotificaciones();
            for (int i = 0; i < notificaciones.size(); i++) {
                Notificacion notificacion = paciente.getNotificaciones().get(i);
                double frecuencia = notificacion.getFrecuenciaCardiaca();
                double presion = notificacion.getPresionSanguinea();
                if (frecuencia == 60 || frecuencia == 100 && presion >= 121 && presion <= 139) {
                    paciente.setEstadoPaciente(paciente.getEstadoAmarillo());
                    System.out.println("El paciente esta en riesgo");
                } else {
                    System.out.println("El paciente no se encuentra en un estado Amarillo");
                }
            }
        }

        public void estadoRojo(){
            System.out.println("El paciente ya se encuentra en estado amarillo");
        }
    }


