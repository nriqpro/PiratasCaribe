/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Tesoro implements Serializable{
    private String nombre;
    private Integer peso;

    public Tesoro(String nombre, Integer peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPeso() {
        return peso;
    }
    
    
}
