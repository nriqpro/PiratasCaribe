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
            
            System.setProperty("java.rmi.server.hostname",g.getIp(m.getNombre()));
             
             
            xml.leerMaquinas(numMaquina);
            m.setIslas(xml.islastemp);
            m.setCayos(xml.cayostemp);
            System.out.println("El tamanio de las islas: "+m.getIslas().size() + " y cayos es de : : "+ m.getCayos().size());
            for (int i =  0 ; i < m.getIslas().size() ; i++){
                System.out.println("Nombre Isla: "+m.getIslas().get(i).getNombre());
                for (int j = 0 ;  j < m.getIslas().get(i).getSitios().size() ; j++){
                    System.out.println("\t "+m.getIslas().get(i).getSitios().get(j).getNombre());
                    for (int k = 0 ; k < m.getIslas().get(i).getSitios().get(j).getCofre().getMapas().size() ; k++){
                        System.out.println("\t\tMapas : cayo " + m.getIslas().get(i).getSitios().get(j).getCofre().getMapas().get(k).getNombreCayo()
                                                        +" o sitio " + m.getIslas().get(i).getSitios().get(j).getCofre().getMapas().get(k).getNombreSitio());
                    }
                    for (int k = 0 ; k < m.getIslas().get(i).getSitios().get(j).getCofre().getTesoros().size() ; k++){
                        System.out.println("\t\tTesoro : " + m.getIslas().get(i).getSitios().get(j).getCofre().getTesoros().get(k).getNombre());
                    }
                    for (int k = 0 ; k < m.getIslas().get(i).getSitios().get(j).getBarcos().size() ; k++){
                        System.out.println("\t\tBarcos: "+m.getIslas().get(i).getSitios().get(j).getBarcos().get(k).getName());
                    }
                }
            }
            
            for (int i = 0 ; i < m.getCayos().size() ; i++){
                System.out.println("Nombre Cayo: " + m.getCayos().get(i).getNombre());
                for (int j = 0 ; j < m.getCayos().get(i).getCofre().getMapas().size() ; j++){
                    System.out.println("\t Mapas: cayo" + m.getCayos().get(i).getCofre().getMapas().get(j).getNombreCayo() +
                                                " o sitio " + m.getCayos().get(i).getCofre().getMapas().get(j).getNombreSitio());
                }
                for (int j = 0 ; j < m.getCayos().get(i).getCofre().getTesoros().size() ; j++){
                    System.out.println("\t Tesoros: " + m.getCayos().get(i).getCofre().getTesoros().get(j).getNombre());
                }
                for (int j = 0 ;  j < m.getCayos().get(i).getBarcos().size() ; j++){
                    System.out.println("\t Barcos: " + m.getCayos().get(i).getBarcos().get(j).getName());
                }
                
            }
            Registry registro = LocateRegistry.createRegistry(g.getPuerto(m.getNombre()));
            String urlServer =  "rmi://"+g.getIp(m.getNombre())+":"+g.getPuerto(m.getNombre())+"/";
            registro.rebind(/*urlServer+*/m.getNombre(), m);
            System.out.println("Ahora esperare a que me llegue una consulta");
            String audioPath = "musica"+numMaquina+".wav";
            Sonido musica = new Sonido(audioPath);
            new Thread(musica).start();
            if (numMaquina==1){
                xml.leerBarcos(1);
                Barco bp = xml.barcotemp;
                
               // Barco bp = new Barco("La_Venganza_Errante",true,20,100,50);
                /*bp.getCofre().agregarTesoro(new Tesoro ("Corazon de la princesa",5));
                bp.getCofre().agregarTesoro(new Tesoro ("Dolares 6,3",10));*/
                Mapa mapaOrigen = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
              //  Mapa mapa1 = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
               // Mapa mapa1 = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
                //Mapa mapa2 = new Mapa("Bahia de la Esperanza","Isla del Naufrago","2");
               // Mapa mapa21 = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
                Mapa mapa3 = new Mapa("Cayo de Barlovento","3");
               /* Mapa mapa4 = new Mapa("Cayo de Sotavento","1");
                Mapa mapa5 = new Mapa("Puerto Rico","La Gran Isla de la Española","4");*/
               // Mapa mapa5 = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
              
                //System.out.println("cayo: "+mapa2.getNombreCayo() + "mapa boolean: "+ mapa2.esIsla());
                bp.setMapaOrigen(mapaOrigen);
                System.out.println("Mapa origen :" + bp.getMapaOrigen().getNombreMaquina());
                
                /*bp.agregarMapa(mapa1);
                bp.agregarMapa(mapa21);*/
                bp.agregarMapa(mapa3);
             /*   bp.agregarMapa(mapa4);
                 bp.agregarMapa(mapa5);*/
                registro.rebind(/*urlServer+*/bp.getName(), bp);
                bp.setMaquinaActual(m.getNombre());
                bp.setMaquinaAnterior(m.getNombre());
                bp.partir();
              //  registro.unbind(bp.getName());
                
                
            }
            if(numMaquina==2){
                xml.leerBarcos(2);
                Barco br1 = xml.barcotemp;
                xml.leerBarcos(3);
                Barco br2 = xml.barcotemp;
                Mapa mapaOrigen = new Mapa("Puerto de la Reina","Isla La Holandesa","2");
                Mapa mapa1 = new Mapa("Bahia del Buen Reposo","Isla La Holandesa","2");
                
                Mapa mapa2 = new Mapa("Bahia de la Esperanza","Isla del Naufrago","2");
             //   Mapa mapa21 = new Mapa("Bahia del Buen Reposo","Isla La Holandesa","2");
              //   Mapa mapa22 = new Mapa("Bahia de la Esperanza","Isla del Naufrago","2");
              //  Mapa mapa23 = new Mapa("Bahia del Buen Reposo","Isla La Holandesa","2");
               //  Mapa mapa24 = new Mapa("Bahia de la Esperanza","Isla del Naufrago","2");
                //Mapa mapa25 = new Mapa("Bahia del Buen Reposo","Isla La Holandesa","2");
                Mapa mapa3 = new Mapa("Cayo de Barlovento","3");
                Mapa mapa4 = new Mapa("Puerto Rico","La Gran Isla de la Española","4");
               // Mapa mapa5 = new Mapa("Puerto Real","Isla Nueva Esperanzas","1");
              
             //   System.out.println("cayo: "+mapa2.getNombreCayo() + "mapa boolean: "+ mapa2.esIsla());
                br1.setMapaOrigen(mapaOrigen);
                br2.setMapaOrigen(mapaOrigen);
               
               //System.out.println("Mapa origen :" + br1.getMapaOrigen().getNombreMaquina());
                    
                
              br1.agregarMapa(mapa1);
              br1.agregarMapa(mapa2);
              
              br2.agregarMapa(mapa3);
              br2.agregarMapa(mapa4);
                
                registro.rebind(br1.getName(), br1);
                br1.setMaquinaActual(m.getNombre());
                br1.setMaquinaAnterior(m.getNombre());
                
                registro.rebind(br2.getName(), br2);
                br2.setMaquinaActual(m.getNombre());
                br2.setMaquinaAnterior(m.getNombre());
                
                br2.partir(); //INTERCEPTOR
                br1.partir(); //INVENCIBLE
                //Thread.sleep((long) (4 * 1000.0));
               // registro.unbind(br1.getName());
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

