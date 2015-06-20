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
public interface InterfazMaquina extends Remote {

    
    // public void recibirBarco (String maquinaOrigen);
     public void recibirBarco (String nombreBarco)throws RemoteException;
    

}
