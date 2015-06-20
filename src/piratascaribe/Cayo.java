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
public class Cayo implements Serializable{
    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    private ArrayList<InterfazBarco>barcos;

    public Cayo(String nombre, Cofre cofre, Calamidad calamidad){
        this.nombre = nombre;
        this.cofre = cofre;
        this.calamidad = calamidad;
        this.barcos = new ArrayList<InterfazBarco>();
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
    
    public ArrayList<InterfazBarco> getBarcos(){
        return barcos;
    }
    public void encallaBarco (InterfazBarco barco){
        if (barcos!=null && barco!= null)
            this.barcos.add(barco);
        else
            System.out.println("Error en Cayo: encallaBarco 'cayos' o 'cayo' null");
        
    }
    
    
}
