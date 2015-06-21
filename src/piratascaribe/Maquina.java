/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;


/**
 *
 * @author user
 */
public class Maquina extends UnicastRemoteObject implements InterfazMaquina {
    private Integer id;
    private ArrayList<Isla> islas;
    private ArrayList<Cayo> cayos;
    private String nombre;
    private Integer numPuertoRMI;
    private Map<String,String> nodos;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    private ArrayList<ArrayList<String>> maquinas;
    
    public Maquina (int id,Integer numPuertoRMI) throws RemoteException{
        
        this.id = id;
        this.nombre = "maquina"+id;
        this.numPuertoRMI = numPuertoRMI;
        this.islas = new ArrayList<Isla>();
        this.cayos = new ArrayList<Cayo>();
        this.nodos = new HashMap<String,String>();
        this.maquinas = new ArrayList<ArrayList<String>>();
        
        Cofre cof = new Cofre(10000);
      
        Calamidad c = new Calamidad("Kraken",1.0,10,10,10);
       /* Isla is = new Isla("Isla1");
        is.addSitio(new Sitio("Sitio1",cof,c));
        is.addSitio(new Sitio("Sitio2",cof,c));
        islas.add(is);*/
        
        nodos.put("maquina1", "localhost");
        nodos.put("maquina2","localhost");
        nodos.put("maquina3","localhost");
        nodos.put("maquina4","localhost");
        nodos.put("servidor","localhost");
        
    }
    @Override 
     public void recibirBarco (String nombreBarco) throws RemoteException{
         try{
           System.out.println("He recibido el barco: "+nombreBarco+" en mis Aguas");
           String urlObjeto ="rmi://localhost:8000/" + nombreBarco; 
           Registry registro = LocateRegistry.getRegistry(8000);
           InterfazBarco barco = (InterfazBarco) Naming.lookup(urlObjeto);
           //hacer aqui procedimiento para dibujar interfaz barco moviendose a destino
           ubicarBarco(barco);
//           barco.marcarMapa()
           izarVelas(barco.getName());
          
          //  System.out.println("Siguiente destino:"+barco.getSiguienteDestino());
        }
        catch(Exception e){
            System.out.println("Error en Maquina: recibirBarco()");
            e.printStackTrace();
        }
     }
     
     public String getNombreMaquina(){
         return this.nombre;
     }
    public String getDireccionMaquina(String maquina){
        return this.nodos.get(maquina);
    }
    
    public void addIsla(Isla isla){
        if (this.islas!=null && isla!=null){
            this.islas.add(isla);
        }
        else
            System.out.println("Error en Maquina: addIsla 'islas' o 'isla' es null");
    }
    public void addCayo(Cayo cayo){
        if (this.cayos!=null && cayo!=null){
            this.cayos.add(cayo);
        }
        else
            System.out.println("Error en Maquina: addCayo 'cayos' o 'cayo' es null");
    }
    
    public void izarVelas(String nombreBarco ) throws RemoteException{
        System.out.println("Debo izarVelas: "+nombreBarco);
        for (int i = 0 ; i < islas.size() ; i++){
            for (int j = 0 ; j < islas.get(i).getSitios().size() ; j++){
                
                for (int k = 0 ; k  < islas.get(i).getSitios().get(j).getBarcos().size() ; k++){
                    
                    if (islas.get(i).getSitios().get(j).getBarcos().get(k).getName().equalsIgnoreCase(nombreBarco)){
                        /*System.out.println("En la isla "+islas.get(i).getNombre() +" sitio "+ islas.get(i).getSitios().get(j).getNombre());
                    System.out.println("\t barco: "+ islas.get(i).getSitios().get(j).getBarcos().get(k).getName());
                        islas.get(i).getSitios().get(j).getBarcos().remove(k);*/
                        InterfazBarco barco = islas.get(i).getSitios().get(j).getBarcos().get(k);
                        
                        int x = barco.getSiguienteDestino();//ACOMODAR
                        if (x >= 0){
                             System.out.println("Siguiente destino:"+ barco.getMapas().get(x).getNombreIsla());
                             System.out.println("partir");
                             barco.partir();
                        }else{
                            System.out.println("He visitado todos mis lugares, me regreso al inicio");
                        }
                        islas.get(i).getSitios().get(j).getBarcos().remove(k);
                        return;
                    }
                }
            }
        }
        
        for (int i = 0 ; i < cayos.size() ; i++){
            for (int j = 0 ; j < cayos.get(i).getBarcos().size() ; j++){
                    if (cayos.get(i).getBarcos().get(j).getName().equalsIgnoreCase(nombreBarco)){
                        /*System.out.println("En el cayo "+ cayos.get(i).getNombre());
                        System.out.println("\t barco: "+ cayos.get(i).getBarcos().get(j).getName());
                        cayos.get(i).getBarcos().remove(j);*/
                        
                        InterfazBarco barco = cayos.get(i).getBarcos().get(j);
                        
                        int x = barco.getSiguienteDestino();
                        if (x >= 0){
                             System.out.println("Siguiente destino:"+ barco.getMapas().get(x).getNombreIsla());//ACOMODAR NO SOLO ISLA
                             System.out.println("partir");
                             barco.partir();
                        }else{
                            System.out.println("He visitado todos mis lugares, me regreso al inicio");
                        }
                        
                        cayos.get(i).getBarcos().remove(j);
                        return;
                    }
                
            }
        }
        //System.out.println();
    }
    public void ubicarBarco(InterfazBarco barco) throws RemoteException{
        try{
            int sigDest = barco.getSiguienteDestino();
            Mapa mapa;
            //System.out.println("Debo ubicarlo en ");
            if (sigDest >= 0){//encontro destino con exito
                barco.marcarMapa(sigDest);//marcamos el sitio donde estabamos anteriormente como visitado
                mapa = barco.getMapas().get(sigDest); //marcamos el mapa en el sitio como actual
                mapa.setEstado("actual");
                if (mapa.esIsla() && islas!=null){
                    for (int i = 0 ; i < islas.size() ; i++){
                        if (islas.get(i).getNombre().equalsIgnoreCase(mapa.getNombreIsla())){
                            ArrayList<Sitio> sitios;
                            sitios = islas.get(i).getSitios();
                            for (int j = 0 ; j < sitios.size() ; j++){
                                if (sitios.get(j).getNombre().equalsIgnoreCase(mapa.getNombreSitio())){
                                    sitios.get(j).encallaBarco(barco);
                                    System.out.println("Lo he ubicado en el sitio: "+sitios.get(j).getNombre());
                                    if (sitios.get(j).getBarcos().size() > 1){ //hay mas de dos barcos encallados 
                                        //verificar que faccion son
                                        //si son diferentes pelear
                                        //si sn 3 barcos pelear de una
                                        System.out.println("Se han encontado dos barcos en la maquina(isla): "
                                                +this.nombre+"IMPLEMENTAR CODIGO PELEA");
                                        
                                    }
                                }
                            }
                        }
                    }
              
                }else{
                    int i;
                    for ( i = 0 ; i < cayos.size() ; i++){
                        if (cayos.get(i).getNombre().equalsIgnoreCase(mapa.getNombreCayo())){
                            cayos.get(i).encallaBarco(barco);
                             System.out.println("Lo he ubicado en el cayo: "+cayos.get(i).getNombre());
                            if (cayos.get(i).getBarcos().size() > 1){
                                //verificar que faccion son
                                 //si son diferentes pelear
                                 //si sn 3 barcos pelear de una
                                 System.out.println("Se han encontado dos barcos en la maquina(cayo): "
                                                +this.nombre+"IMPLEMENTAR CODIGO PELEA");
                            }
                        }
                    }
                    if (i == cayos.size()){
                        System.out.println("Error en Maquina: Nombre de Cayo no se logra ubicar en esta maquina");
                    }
                }
              
            }else{ //no encontro destino
                System.out.println("Error Maquina: ubicarBarco no se ha encontrado siguente destino");
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
