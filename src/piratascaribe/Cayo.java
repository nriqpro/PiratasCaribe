/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Cayo {
    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    private ArrayList<Barco>barcos;

    public Cayo(String nombre, Cofre cofre, Calamidad calamidad){
        this.nombre = nombre;
        this.cofre = cofre;
        this.calamidad = calamidad;
        this.barcos = new ArrayList<Barco>();
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
    
    
    
}
