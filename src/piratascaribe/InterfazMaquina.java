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
    public String getNombre() throws RemoteException;
    public void recibirBarco (String nombreBarco,String nombreMaquina)throws RemoteException;
    public void eliminarReferenciaBarco (String nombreBarco, String nombreMaquinaAnterior) throws RemoteException;
    

}
