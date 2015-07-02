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

    public Tesoro(String nombre) {
        this.nombre = nombre;
        determinarPeso();
    }

    public String getNombre() {
        return nombre;
    }
    
    public Integer getPeso() {
        return peso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void determinarPeso(){
        String[] tesoros = {"Mapa","Barra de Oro","Barra de Plata","Bolsa de Perlas","Bolsa de Monedas de Oro","Cofre de Joyas","Cofre de Piedras Preciosas","Corazon de la Princesa"}; 
        for(int i=0; i<tesoros.length;i++){
            if(nombre.equalsIgnoreCase(tesoros[i])){
                switch(i){
                    case 0: peso = new Integer(5);
                            break;
                    case 1: peso = new Integer(25);
                            break;
                    case 2: peso = new Integer(50);
                            break;
                    case 3: peso = new Integer(10);
                            break;
                    case 4: peso = new Integer(15);
                            break;
                    case 5: peso = new Integer(15);
                            break;
                    case 6: peso = new Integer(10);
                            break;
                    default: peso = new Integer(5);
                            break;
                }
                return;
            }
        }
    }
    
}
