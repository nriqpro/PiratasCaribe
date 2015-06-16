/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.rmi.*;


/**
 *
 * @author user
 */
public class Maquina extends UnicastRemoteObject implements InterfazMaquina {
    private ArrayList<Isla> islas;
    private ArrayList<Cayo> cayos;
    private String nombre;
    private Integer numPuertoRMI;
    private Map<String,String> nodos;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    
    
    public Maquina (String nombre,Integer numPuertoRMI) throws RemoteException{
        
        this.nombre = nombre;
        this.numPuertoRMI = numPuertoRMI;
        this.islas = new ArrayList<Isla>();
        this.cayos = new ArrayList<Cayo>();
        
        nodos.put("maquina1", "localhost");
        nodos.put("maquina2","localhost");
        nodos.put("maquina3","localhost");
        nodos.put("maquina4","localhost");
        nodos.put("servidor","localhost");
        
    }
    @Override 
     public void recibirBarco (String nombreBarco){
         try{
           String urlObjeto ="rmi://" + nodos.get("servidor") +":8000/" + nombreBarco; 
           Barco barco = (Barco) Naming.lookup(urlObjeto);
           //hacer aqui procedimiento para dibujar interfaz barco moviendose a destino
           ubicarBarco(barco);
            System.out.println("partir");
            System.out.println("Siguiente destino:"+barco.getSiguienteDestino());
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
    
    
    public void ubicarBarco(Barco barco) throws RemoteException{
        try{
            int sigDest = barco.getSiguienteDestino();
            Mapa mapa;
            if (sigDest <= 0){//encontro destino con exito
                barco.marcarMapa();//marcamos el sitio donde estabamos anteriormente como visitado
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
              
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
