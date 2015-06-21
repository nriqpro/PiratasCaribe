/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Isla implements Serializable{
    private String nombre;
   // private String maquina;
    private ArrayList<Sitio> sitios;
    
    public Isla(String nombre){
        this.nombre = nombre;
        this.sitios = new ArrayList<Sitio>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Sitio> getSitios() {
        return sitios;
    }
    
    public void addSitio(Sitio sitio){
        if (sitios!=null && sitio!=null)
            this.sitios.add(sitio);
        else
            System.out.println("Error Isla: addSitio 'islas' o 'isla' es null");
    }
    
    public void setSitios(ArrayList<Sitio> sitios){
        if (sitios!=null){
            this.sitios = sitios;
        }
            System.out.println("Error Isla: setSitios 'sitios' es null");
    }
    
    
    
    
}
