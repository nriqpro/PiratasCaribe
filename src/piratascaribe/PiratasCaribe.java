/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PiratasCaribe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String URLregistro;
        int puertoRMI=8000;
        try{
            //codigo que permite obtener el valor del numero del puerto 
            //ImplEjemplo objExportado = new ImplEjemplo();
            arrancarRegistro(puertoRMI);
            
            //registrar el objeto bajo el nombre "ejemplo"
            Barco bp = new Barco("Venganza_Errante",true,10,10,10);
            URLregistro = "rmi://localhost:"+ puertoRMI +"/"+bp.getName();
            Naming.rebind(URLregistro, bp);
            System.out.println("Servidor Ejemplo Preparado ya he enviado el barco");
        }
        catch (Exception e){
            System.out.println("Excepcion en ServidorEjemplo.main: "+ e);
        }
        // TODO code application logic here
       /* ArrayList<String> ejemplo = new ArrayList<String>();
        ejemplo.add("Uno");
        System.out.println("Hola mundo piratas "+ejemplo.get(0));
        Calamidad c = new Calamidad("Crak",new Float(0.1),-10,-10,-10);
        for (int i = 0 ; i < 10 ; i++){
            if (c.ocurreCalamidad())
                System.out.println("Calamidad: "+c.getNombre()+"ha ocurrido");
            else
                System.out.println("No ha ocurido nada");
        }*/
        
        ClienteEjemplo cliente = new ClienteEjemplo();
        cliente.ejecutar();
    }
    
    private static void arrancarRegistro (int puertoRMI)throws RemoteException{
        try{
            Registry registro = LocateRegistry.getRegistry(puertoRMI);
            registro.list();
            //El metodo anterior lanza una excepcion
            //Si el registro no existe
        }
        catch (RemoteException e){
            //No existe un registro valido en este puerto 
            System.out.println("El registro RMI no se puede localizar en el puerto: "+puertoRMI);
            Registry registro = LocateRegistry.createRegistry(puertoRMI);
            System.out.println("Registro RMI creado en el puerto: "+ puertoRMI);
        }
    }
    
}
//comentario