/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import java.util.ArrayList;
import java.util.List;
import models.Medico;
import models.Notificacion;
import models.Paciente;

/**
 *
 * @author yf.rivera1851
 */
public abstract class AlertMediator implements IMediator<Notificacion> {
    

 
 public List<IColleague> colleagueList = new ArrayList<IColleague>();

  public List getAll()
  {
      return colleagueList;
  }

   public void Register(IColleague<Notificacion> colleague)
    {
        colleagueList.add(colleague);
    }

    public void DistributeMessage(IColleague<Notificacion> sender, String message)
    {
       for(IColleague e : colleagueList){
           if(e != sender)
               e.ReceiveMessage(message);
       }
      
    }
    
    public void notify(Paciente p,Medico m, Notificacion n)
    {
        List<Notificacion> medicos = m.getNotificaciones();
        List<Notificacion> pacientes = p.getNotificaciones();
        
        medicos.add(n);
        pacientes.add(n);
        Register(n);
        
        String message = "Se ha añadido una nueva notificacion de alerta";
        
        DistributeMessage(n, message);
        
        
        
        
    }
   
    
    
    
    
    
    
}
