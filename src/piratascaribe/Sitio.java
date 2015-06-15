/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

/**
 *
 * @author user
 */
public class Sitio {
    private String nombre;
    private Cofre cofre;
    private Calamidad calamidad;
    
    public Sitio(String nombre, Cofre cofre, Calamidad calamidad) {
        this.nombre = nombre;
        this.cofre = cofre;
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
    
    
}
