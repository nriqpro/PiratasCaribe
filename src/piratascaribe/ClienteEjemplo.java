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
    
    private static int puertoRMI = 8000;
    private String nombreNodo;
    private String numPuerto;
    private String URLRegistro = "rmi://localhost:"+puertoRMI+"/Venganza_Errante";
    //Codigo que permite obtner el nombre del nodo 
    //y el numero de puerto del registro
    
    //Bussqueda del objeto remoto y cast de la
    //referencia con la correspondiente clase
    //de la interfaz remoto - reemplazar localhost por el nombre
    // del nodo del objeto remoto.
    
   /* public void ejecutar(){
        try{
            InterfazBarco h = (InterfazBarco) Naming.lookup(this.URLRegistro);
            //invocar el o los metodos remotos
            
      
            
            h.imprimirCofre();

            //El metodo metodoEj2 puede invocarse del mismo nodo
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    //METODO PARA PARTIR A LA SIGUIENTE UBICACION EN LA ISLA    
    
        
        //posible definicion de otros metodos de la clase
    }*/
  
    public void partir(String url) {
        try{
           // String urlServer ="rmi://localhost:8000/Venganza_Errante";
            InterfazBarco barco = (InterfazBarco) Naming.lookup(url);
            System.out.println("partir");
            int i = barco.getSiguienteDestino();
            System.out.println("Siguiente destino:"+ barco.getMapas().get(i).getNombreIsla());
            Naming.rebind(url, barco);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("Epale soy cliente");
        String urlServer ="localhost";
        int puertoServer = 8000;
        XMLParser xml = new XMLParser();
        
        try {
            InputStreamReader leer = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(leer);
            System.out.print("Escriba el id: ");
            String nombreMaquina = buff.readLine();
            int numMaquina = Integer.parseInt(nombreMaquina);
            System.out.println("Soy maquina: "+nombreMaquina);
            Maquina m = new Maquina(numMaquina,8001);
            xml.leerMaquinas(numMaquina);
            m.setIslas(xml.islastemp);
            m.setCayos(xml.cayostemp);
            for (int i =  0 ; i < m.getIslas().size() ; i++){
                System.out.println("Nombre Isla: "+m.getIslas().get(i).getNombre());
                for (int j = 0 ;  j < m.getIslas().get(i).getSitios().size() ; j++){
                    System.out.println("\t "+m.getIslas().get(i).getSitios().get(j).getNombre());
                }
            }
            
            for (int i = 0 ; i < m.getCayos().size() ; i++){
                System.out.println("Nombre Cayo: " + m.getCayos().get(i).getNombre());
            }
            //System.out.println("Soy maquina: "+nombreMaquina);
            Naming.rebind("rmi://localhost:8000/"+m.getNombreMaquina(), m);
            System.out.println("Ahora esperare a que me llegue una consulta");
            
            if (numMaquina==1){
                xml.leerBarcos(1);
                Barco bp = xml.barcotemp;
                /*bp.getCofre().agregarTesoro(new Tesoro ("Corazon de la princesa",5));
                bp.getCofre().agregarTesoro(new Tesoro ("Dolares 6,3",10));*/
                Mapa mapa1 = new Mapa("maquina1","Isla Nueva Esperanzas","Puerto Real","Cayo del Buen Viento",true);
                Mapa mapa2 = new Mapa("maquina2","Isla del Naufrago","Puerto de La Reina","Cayo del Buen Viento",true);

                bp.agregarMapa(mapa1);
                bp.agregarMapa(mapa2);
                String URLregistro = "rmi://localhost:"+ puertoRMI +"/"+bp.getName();
                Naming.rebind(URLregistro, bp);
                bp.partir();
                
                
            }
            
        }catch(Exception e){
            System.out.println("Error en Maquina ");
            e.printStackTrace();
        }
    }
}

