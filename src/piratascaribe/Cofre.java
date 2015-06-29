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
public class Cofre implements Serializable{
    private Integer capacidad;
    private ArrayList<Tesoro> tesoros;
    private ArrayList<Mapa> mapas;

    public Cofre(Integer capacidad) {
        this.capacidad = capacidad;
        this.tesoros = new ArrayList<Tesoro>();
        this.mapas = new ArrayList<Mapa>();
    }
    
    public void agregarTesoro (Tesoro tesoro){
        if ((this.getPeso() + tesoro.getPeso()) <= capacidad) 
            tesoros.add(tesoro);
        else
            System.out.println("Agregar Tesoro: no se pudo agregar objeto supera el limite de la capacidad");
    }
    
    public void eliminarTesoro(int i){
        tesoros.remove(i);
    }
    
    public ArrayList<Tesoro> getTesoros(){
        return tesoros;
    }

    public ArrayList<Mapa> getMapas() {
        return mapas;
    }

    public void setMapas(ArrayList<Mapa> mapas) {
        this.mapas = mapas;
    }
    
    public Integer getPeso(){
        Integer peso = new Integer(0);
        
        for (int i=0 ; i < tesoros.size() ; i++){
            peso += tesoros.get(i).getPeso();
        }
        
        for (int i= 0 ; i < mapas.size() ; i++){
            peso += 5;
        }
        
        return peso;
    }
    
     public Integer getValor(){
        Integer valor = new Integer(0);
        Tesoro t;
        int valorTesoroPrincesa = 10000;
        int valorOtros = 1;
        for (int i=0 ; i < tesoros.size() ; i++){
            t = tesoros.get(i);
            if (t.getNombre().equalsIgnoreCase("Corazon de La Princesa"))
                valor += valorTesoroPrincesa;
            else
                valor += valorOtros;
        }
        
        for (int i= 0 ; i < mapas.size() ; i++){
            valor += valorOtros;
        }
        return valor;
    }
    
    
}
