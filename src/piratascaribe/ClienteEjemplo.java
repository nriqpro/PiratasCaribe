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
    
   /* private static int puertoRMI = 8000;
    private String nombreNodo;
    private String numPuerto;*/
   // private String URLRegistro = "rmi://192.168.0.114:"+puertoRMI+"/Venganza_Errante";
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
       /* String ipServer ="192.168.1.102";
        int puertoServer = 8000;*/
        
        XMLParser xml = new XMLParser();
        
        try {
            InputStreamReader leer = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(leer);
            System.out.print("Escriba el id: ");
            String nombreMaquina = buff.readLine();
            int numMaquina = Integer.parseInt(nombreMaquina);
            System.out.println("Soy maquina: "+nombreMaquina);
            GestorRMI g = new GestorRMI();
            Maquina m = new Maquina(numMaquina,g.getPuerto("maquina"+numMaquina));
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
            //Naming.rebind("rmi://192.168.0.114:8000/"+m.getNombreMaquina(), m);
            Registry registro = LocateRegistry.createRegistry(g.getPuerto(m.getNombre()));
           // Registry registro = LocateRegistry.getRegistry(g.getIp(m.getNombre()), g.getPuerto(m.getNombre()));
            String urlServer =  "rmi://"+g.getIp(m.getNombre())+":"+g.getPuerto(m.getNombre())+"/";
           //registro.rebind(urlServer+m.getNombre(), m);*/
            registro.rebind(/*urlServer+*/m.getNombre(), m);
           /* InterfazServidor is = (InterfazServidor)registro.lookup("server");
            is.registroRebind(m, 2);*/
            System.out.println("Ahora esperare a que me llegue una consulta");
            
            if (numMaquina==1){
                xml.leerBarcos(1);
                Barco bp = xml.barcotemp;
               // Barco bp = new Barco("La_Venganza_Errante",true,20,100,50);
                /*bp.getCofre().agregarTesoro(new Tesoro ("Corazon de la princesa",5));
                bp.getCofre().agregarTesoro(new Tesoro ("Dolares 6,3",10));*/
                Mapa mapa1 = new Mapa("maquina1","Isla Nueva Esperanzas","Puerto Real","Cayo del Buen Viento",true);
                Mapa mapa2 = new Mapa("maquina2","Isla La Holandesa","Puerto de La Reina","Cayo del Buen Viento",true);
                
                bp.agregarMapa(mapa1);
                bp.agregarMapa(mapa2);
                registro.rebind(/*urlServer+*/bp.getName(), bp);
              //  registro.rebind(urlServer+bp.getName(), m);
                
                Registry registrom2 = LocateRegistry.getRegistry(g.getIp("maquina2"),g.getPuerto("maquina2"));
                System.out.println("Imprimo Los Objetos Guardados En Maquina 2");
                String[] names = registrom2.list();
                for (int i = 0; i < names.length; i++)
			System.out.println(names[i]);
                bp.setMaquinaActual(m.getNombre());
                bp.setMaquinaAnterior(m.getNombre());
                bp.partir();
                
                
            }
            String[] names = registro.list();
            System.out.println("Imprimo Los Objetos Guardados");
                for (int i = 0; i < names.length; i++)
			System.out.println(names[i]);
        }catch(Exception e){
            System.out.println("Error en Maquina ");
            e.printStackTrace();
        }
    }
}

