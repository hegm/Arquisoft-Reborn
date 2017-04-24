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
public interface IMediator<t> {
    
    
void DistributeMessage(IColleague<t> sender, String message);

void Register(IColleague<t> colleague);
    
}
