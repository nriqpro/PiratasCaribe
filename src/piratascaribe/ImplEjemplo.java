/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author user
 */
public class ImplEjemplo extends UnicastRemoteObject implements InterfazEjemplo{
    public ImplEjemplo() throws RemoteException{
        super();
    }
    
    @Override
    public String metodoEj1() throws RemoteException{
        //codigo del metodo
        return "Hola soy el metodo Ej1";
    }
    @Override
    public int metodoEj2() throws RemoteException{
        //codigo del metodo
        return 1;
    }
}
