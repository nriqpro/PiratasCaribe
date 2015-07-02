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
public class Sitio implements Serializable {
    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    private ArrayList<Barco>barcos;
    
    
    public Sitio(String nombre, Cofre cofre, Calamidad calamidad){
        this.nombre = nombre;
        this.cofre = cofre;
        //this.calamidad = calamidad;
        this.barcos = new ArrayList<Barco>();
    }
    public Sitio(){
        this.barcos = new ArrayList<Barco>();
    }

    public void setCalamidad(Calamidad calamidad) {
        this.calamidad = calamidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public Calamidad getCalamidad() {
        return calamidad;
    }
    
    public ArrayList<Barco> getBarcos(){
        return barcos;
    }
    
    public void encallaBarco (Barco barco){
        if (barcos!=null && barco!= null){
            this.barcos.add(barco);
            barco.cargarCofre(this.cofre);
          /* if (barco.cargarCofre(this.cofre)==0){
               this.barcos.remove(barco);
               try{
                   System.out.println("Enhorabuena has conseguido el corazon de la princesa y te devuelves al origen");
                   barco.partirOrigen();
               }catch(Exception e){
                   System.out.println("Error sitio: encalla barco");
                   e.printStackTrace();
               }
           }*/
        }
        else
            System.out.println("Error en Sitio: encallaBarco 'barcos' o 'barco' null");
        
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }
    

    
    
}
