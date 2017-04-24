/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

/**
 *
 * @author yf.rivera1851
 */
public interface IColleague<t> {
    
    void SendMessage(String message);

    void ReceiveMessage(String message);
    
}
