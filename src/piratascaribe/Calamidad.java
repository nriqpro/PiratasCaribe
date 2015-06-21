/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author user
 */
public class Calamidad implements Serializable{
    private String nombre;
    private Double probabilidad;
    private Integer resta_trip;
    private Integer resta_racion;
    private Integer resta_ammo;

    public Calamidad(String nombre, Double probabilidad, Integer resta_trip, Integer resta_racion, Integer resta_ammo) {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
        this.resta_trip = resta_trip;
        this.resta_racion = resta_racion;
        this.resta_ammo = resta_ammo;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getResta_trip() {
        return resta_trip;
    }

    public Integer getResta_racion() {
        return resta_racion;
    }

    public Integer getResta_ammo() {
        return resta_ammo;
    }

    
    
    public Boolean ocurreCalamidad(){
           Random rand = new Random();
           int max = 10;
           int min = 1;

           int randomNum = rand.nextInt((max - min) + 1) + min;
           if (randomNum <= (this.probabilidad * 10))
                return true;
           else
               return false;
    }
    
}
