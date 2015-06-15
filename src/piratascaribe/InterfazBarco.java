/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.rmi.*;

/**
 *
 * @author user
 */
public interface InterfazBarco extends Remote{
    
    public String metodoEj1()throws java.rmi.RemoteException;
        
    
    
    public int metodoEj2()throws java.rmi.RemoteException;
    
     public void imprimirCofre() throws RemoteException;
    
}
