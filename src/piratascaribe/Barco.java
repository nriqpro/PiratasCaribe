/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

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
    private ArrayList<String> rutas =
    
    private Cofre cofre;
    private String puertoOrigen;
    //private ArrayList<Kapa> mapas;

    public Barco(String nombre, Boolean pirata, Integer nTripulacionOriginal, Integer nRacionesOriginal, Integer nAmmoOriginal) throws RemoteException{
        this.nombre = nombre;
        this.pirata = pirata;
        this.nTripulacionOriginal = nTripulacionOriginal;
        this.nRacionesOriginal = nRacionesOriginal;
        this.nAmmoOriginal = nAmmoOriginal;
        if (pirata){
            //crear cofree con capacidad 100
            this.cofre = new Cofre(100);
        }
        else
            this.cofre = new Cofre(50);
                
    }
    
    @Override
    public String metodoEj1() throws RemoteException{
        //codigo del metodo
        
        return nombre;
    }
    @Override
    public int metodoEj2() throws RemoteException{
        //codigo del metodo
        return 1;
    }

    public String getName(){
        return nombre;
    }
    public Integer getnTripulacion() {
        return nTripulacion;
    }

    public void setnTripulacion(Integer nTripulacion) {
        this.nTripulacion = nTripulacion;
    }

    public Integer getnAmmo() {
        return nAmmo;
    }

    public void setnAmmo(Integer nAmmo) {
        this.nAmmo = nAmmo;
    }

    public Integer getnTripulacionOriginal() {
        return nTripulacionOriginal;
    }

    public void setnTripulacionOriginal(Integer nTripulacionOriginal) {
        this.nTripulacionOriginal = nTripulacionOriginal;
    }
    
    
    
   
}
