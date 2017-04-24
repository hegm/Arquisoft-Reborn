package state;

import models.Notificacion;
import models.Paciente;

import java.util.List;

/**
 * Created by haes_ on 23/04/2017.
 */
public class EstadoVerde implements IEstadoPaciente {

    Paciente paciente;

    public EstadoVerde(Paciente nuevoPaciente)
    {
        paciente=nuevoPaciente;
    }

    public void estadoVerde(){
        List notificaciones = paciente.getNotificaciones();
        for(int i=0; i<notificaciones.size();i++ ){
            Notificacion notificacion = paciente.getNotificaciones().get(i);
            double frecuencia=notificacion.getFrecuenciaCardiaca();
            double presion= notificacion.getPresionSanguinea();
            if(frecuencia>=50 && frecuencia<=100 && presion>=79 && presion<=120){
                paciente.setEstadoPaciente(paciente.getEstadoVerde());
                System.out.println("El paciente esta sano");
            }
            else{
                System.out.println("El paciente no se encuentra en un estado verde");
            }
        }
    }

    public void estadoAmarillo(){
        System.out.println("El paciente ya se encuentra en estado verde");
    }

    public void estadoRojo(){
        System.out.println("El paciente ya se encuentra en estado verde");
    }
}
