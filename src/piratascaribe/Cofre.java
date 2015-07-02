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
    
    public int agregarTesoro (Tesoro tesoro){
        if ((this.getPeso() + tesoro.getPeso()) <= capacidad) {
            tesoros.add(tesoro);
            return 0;
        }
        else{
            return 1;
           // System.out.println("Agregar Tesoro: no se pudo agregar objeto supera el limite de la capacidad");
        }
    }
    
      public int agregarMapa(Mapa mapa){
        if ((mapas!=null && (this.getPeso() + 5 <= capacidad) )) {
            mapas.add(mapa);
            return 0;
        }
        else{
            return 1;
           // System.out.println("Agregar Tesoro: no se pudo agregar objeto supera el limite de la capacidad");
        }
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
     
     public int poseeCorazon(){
         for (int i = 0 ; i < tesoros.size() ; i ++){
             if (tesoros.get(i).getNombre().equalsIgnoreCase("Corazon de la Princesa")){
                 return i;
             }
         }
         return -1;
     }
    
    public int getTesoroMenorPeso(){
        int menor = 0;
        for (int i = 0 ; i < tesoros.size() ; i++){
            if (tesoros.get(menor).getPeso() >= tesoros.get(i).getPeso())
                menor = i;
        }
        return menor;
    }
     
    public int eliminarMapa(int i){
        if (mapas!=null && i <= mapas.size()){
            mapas.remove(i);
            return 0;
        }
        return 1;   
    }
}
