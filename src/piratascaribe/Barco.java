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
public class Barco {
    private String nombre;
    private Boolean pirata;
    private Integer nTripulacion;
    private Integer nRaciones;
    private Integer nAmmo;
    private Integer nTripulacionOriginal;
    private Integer nRacionesOriginal;
    private Integer nAmmoOriginal;
    
    private Cofre cofre;
    private String puertoOrigen;
    //private ArrayList<Kapa> mapas;

    public Barco(String nombre, Boolean pirata, Integer nTripulacionOriginal, Integer nRacionesOriginal, Integer nAmmoOriginal) {
        this.nombre = nombre;
        this.pirata = pirata;
        this.nTripulacionOriginal = nTripulacionOriginal;
        this.nRacionesOriginal = nRacionesOriginal;
        this.nAmmoOriginal = nAmmoOriginal;
        /*if (pirata){
            //crear cofree con capacidad 100
        }
        else
            //crear cofre con capacidad 50
                */
    }

    public Integer getnTripulacion() {
        return nTripulacion;
    }

    public void setnTripulacion(Integer nTripulacion) {
        this.nTripulacion = nTripulacion;
    }

    public Integer getnAmmo() {
        return nAmmo;
    }

    public void setnAmmo(Integer nAmmo) {
        this.nAmmo = nAmmo;
    }

    public Integer getnTripulacionOriginal() {
        return nTripulacionOriginal;
    }

    public void setnTripulacionOriginal(Integer nTripulacionOriginal) {
        this.nTripulacionOriginal = nTripulacionOriginal;
    }
    
    
    
   
}
