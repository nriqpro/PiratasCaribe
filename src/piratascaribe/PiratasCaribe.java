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
import java.util.Map;

/**
 *
 * @author user
 */
public class PiratasCaribe implements InterfazServidor {
        static int puertoServer=8000;
        String ipServer = "192.168.1.102";
        String urlServer = "rmi://"+ipServer+":"+puertoServer+"/";
    public static void main(String[] args) {
       // String URLregistro;
        //int puertoServer=8000;
       /* String ipServer = "192.168.1.102";
        String urlServer = "rmi://"+ipServer+":"+puertoServer+"/";*/
       // Map<String,String> nodos;
        try{    
            arrancarRegistro(puertoServer);
            System.out.println("Servidor Ejemplo Preparado ya he enviado el barco");      
        }
        catch (Exception e){
            System.out.println("Excepcion en ServidorEjemplo.main: "+ e);
        } 
    }
    
    @Override
    public void registroRebind(Remote objeto,int tipo) throws RemoteException{
        try {
            switch (tipo){
                case 1:
                    InterfazBarco barco= (InterfazBarco)objeto;
                    Naming.rebind(urlServer+barco.getName(),barco);
                    break;
                case 2:
                    InterfazMaquina maquina = (InterfazMaquina) objeto;
                    Naming.rebind(urlServer+maquina.getNombre(),maquina);
                    break;
                default:
                    System.out.println("registroRebind entro en default metodo no implementado para tipo:" + tipo);
                    break;

            }
        }catch (Exception e){
            System.out.println("Error en PiratasCaribe(servidor): registroRebind");
            e.printStackTrace();
        }
        
    }
    
    private static void arrancarRegistro (int puertoRMI)throws RemoteException{
        try{
            Registry registro = LocateRegistry.getRegistry(puertoRMI);
            registro.list();
            //El metodo anterior lanza una excepcion
            //Si el registro no existe
            System.out.println("Se ha arrancado el registro en el Server");
        }
        catch (RemoteException e){
            //No existe un registro valido en este puerto 
            System.out.println("El registro RMI no se puede localizar en el puerto: "+puertoRMI);
            Registry registro = LocateRegistry.createRegistry(puertoRMI);
            System.out.println("Registro RMI creado en el puerto: "+ puertoRMI);
            while(true){}
                

        }
    }
    
}
//comentario