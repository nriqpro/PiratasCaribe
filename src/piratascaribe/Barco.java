/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Barco extends UnicastRemoteObject implements InterfazBarco{
    private String nombre;
    private Boolean pirata;
    private Integer nTripulacion;
    private Integer nRaciones;
    private Integer nAmmo;
    private Integer nTripulacionOriginal;
    private Integer nRacionesOriginal;
    private Integer nAmmoOriginal;
    private String maquinaOrigen;
    private ArrayList<Mapa> mapas;
     private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    
    private Cofre cofre;
    private String puertoOrigen;
    //private ArrayList<Kapa> mapas;

    public Barco(String nombre, Boolean pirata, Integer nTripulacionOriginal, Integer nRacionesOriginal, Integer nAmmoOriginal) throws RemoteException{
        this.nombre = nombre;
        this.pirata = pirata;
        this.nTripulacionOriginal = nTripulacionOriginal;
        this.nRacionesOriginal = nRacionesOriginal;
        this.nAmmoOriginal = nAmmoOriginal;
        this.nAmmo =nAmmoOriginal;
        this.nTripulacion = nTripulacionOriginal;
        this.nRaciones = nRacionesOriginal;
        this.mapas = new ArrayList<Mapa>();
        if (pirata){
            //crear cofree con capacidad 100
            this.cofre = new Cofre(100);
        }
        else
            this.cofre = new Cofre(50);
                
    }

    public void setMaquinaOrigen(String maquinaOrigen) {
        this.maquinaOrigen = maquinaOrigen;
    }

    public void setPuertoOrigen(String puertoOrigen) {
        this.puertoOrigen = puertoOrigen;
    }
    

    @Override
    public void imprimirCofre() throws RemoteException{
        
       
        //codigo del metodo
        if (cofre!=null && cofre.getTesoros()!=null){
            if (cofre.getTesoros().size() == 0){
                System.out.println("El cofre no posee ningun tesoro :(");
            }
            else{
                System.out.println("Nombre \t\t Peso");
                for (int i = 0 ; i < cofre.getTesoros().size() ; i++){
                    System.out.println(cofre.getTesoros().get(i).getNombre()+" -> "+cofre.getTesoros().get(i).getPeso());
                }
            }
        }
        else{
            System.out.println("Error en Barco: imprimirCofre el cofre o arrayTesoros es null");
        }
        
    }

    
    public String getName() {
        return nombre;
    }
    @Override
    public Integer getnTripulacion() throws RemoteException {
        return nTripulacion;
    }

    public Integer getnRaciones() throws RemoteException {
        return nRaciones;
    }

    public void setnRaciones(Integer nRaciones) throws RemoteException{
        this.nRaciones = nRaciones;
    }

    
    public void setnTripulacion(Integer nTripulacion)throws RemoteException {
        this.nTripulacion = nTripulacion;
    }

    public Integer getnAmmo() throws RemoteException {
        return nAmmo;
    }

    public void setnAmmo(Integer nAmmo) throws RemoteException{
        this.nAmmo = nAmmo;
    }

    public Integer getnTripulacionOriginal() {
        return nTripulacionOriginal;
    }

    public void setnTripulacionOriginal(Integer nTripulacionOriginal) {
        this.nTripulacionOriginal = nTripulacionOriginal;
    }
    
    public Cofre getCofre(){
        return this.cofre;
    }
    
    public ArrayList<Mapa> getMapas(){
        return this.mapas;
    }
    
    public int agregarMapa(Mapa mapa){
        if (this.mapas!=null){
            this.mapas.add(mapa);
            return 1;
        }
        else{
            System.out.println("Error barco:  agregarMapa el arreglo de mapas es Null");
            return -1;
        }
    }
    
    @Override
    public int getSiguienteDestino() throws RemoteException{
       // return "No Implementado";
        for (int i = 0 ; i < mapas.size() ; i++){
            if (mapas.get(i).getEstado().equalsIgnoreCase(no_visitado))
                    return i;
          /*  if (mapas.get(i).esIsla()){// si es isla devuelve el nombre de la isla
                if (mapas.get(i).getEstado().equalsIgnoreCase("no visitado"))
                    return i;
            }
            else{// si es cayo devuelve el nombre
                if (mapas.get(i).getEstado().equalsIgnoreCase("no visitado"))
                    return i;
            }*/
        }
        return -1;
    }
    
    @Override
    public int marcarMapa(int i) throws RemoteException{
       // return "No Implementado";
        
        if (i < mapas.size() ){
            mapas.get(i).setEstado(visitado);
            return 1;
        }
        else {
            return -1;
        }
        /*for (int i = 0 ; i < mapas.size() ; i++){
            if (mapas.get(i).getEstado().equalsIgnoreCase(actual)){
                    mapas.get(i).setEstado(visitado);
                    return 1;
            }
 
        }*/

    }
    
    @Override
    public void partir() throws RemoteException{
        String urlServer = "localhost";
        int puertoServer = 8000;
       InterfazMaquina machine;
       //Thread.sleep((long) (10 * 1000.0));
        try {
            Thread.sleep((long) (5 * 1000.0));
            machine = (InterfazMaquina)Naming.lookup("rmi://"+urlServer+":"+puertoServer+"/"+mapas.get(this.getSiguienteDestino()).getNombreMaquina());
            machine.recibirBarco(this.getName());
        } catch (Exception e) {
            System.out.println("Error en Barco : partir()");
            e.printStackTrace();
        }
     
     
    }
    
   
}
