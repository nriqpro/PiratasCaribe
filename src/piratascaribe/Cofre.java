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
public class Cofre {
    private Integer Capacidad;
    private ArrayList<Tesoro> tesoros;

    public Cofre(Integer Capacidad) {
        this.Capacidad = Capacidad;
        this.tesoros = new ArrayList<Tesoro>();
    }
    
    public void agregarTesoro (Tesoro tesoro){
        tesoros.add(tesoro);
    }
    
    public Integer getPeso(){
        Integer peso = new Integer(0);;
        
        for (int i=0 ; i < tesoros.size() ; i++){
            peso += tesoros.get(i).getPeso();
        }
        
        return peso;
    }
    
     /*public Integer getValor(){
        Integer valor = new Integer(0);
        Tesoro t;
        for (int i=0 ; i < tesoros.size() ; i++){
            t = tesoros.get(i);
            if (t.getNombre().equalsIgnoreCase("Corazon de La Princesa"))
            valor += tesoros.get(i).ge;
        }
        
        return valor;
    }*/
    
    
}
