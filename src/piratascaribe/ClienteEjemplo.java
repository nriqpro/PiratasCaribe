/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author user
 */
public class ClienteEjemplo {
    
    private int puertoRMI = 8000;
    private String nombreNodo;
    private String numPuerto;
    private String URLRegistro = "rmi://localhost:"+puertoRMI+"/Venganza_Errante";
    //Codigo que permite obtner el nombre del nodo 
    //y el numero de puerto del registro
    
    //Bussqueda del objeto remoto y cast de la
    //referencia con la correspondiente clase
    //de la interfaz remoto - reemplazar localhost por el nombre
    // del nodo del objeto remoto.
    
    public void ejecutar(){
        try{
            InterfazBarco h = (InterfazBarco) Naming.lookup(this.URLRegistro);
            //invocar el o los metodos remotos
            
           /* String mensaje = h.metodoEj1();
            System.out.println(mensaje);*/
            
            h.imprimirCofre();

            //El metodo metodoEj2 puede invocarse del mismo nodo
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    //METODO PARA PARTIR A LA SIGUIENTE UBICACION EN LA ISLA    
    
        
        //posible definicion de otros metodos de la clase
    }
  
    public void partir(Barco barco){
        System.out.println("partir");
        System.out.println("Siguiente destino:"+barco.getSiguienteDestino());
    }
}
