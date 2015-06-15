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
public class Mapa {
    
    private String maquina;
    private String isla;
    private String sitio;
    private String cayo;
    private Boolean tipoLugar; //verdadero si es isla, si no es cayo

    public Mapa(String maquina, String isla, String sitio, String cayo, Boolean tipoLugar) {
        this.maquina = maquina;
        this.isla = isla;
        this.sitio = sitio;
        this.cayo = cayo;
        this.tipoLugar = tipoLugar;
    }
    
    public Boolean esIsla(){
        return tipoLugar;
    }

    public String getMaquina() {
        return maquina;
    }

    public String getIsla() {
        return isla;
    }

    public String getSitio() {
        return sitio;
    }

    public String getCayo() {
        return cayo;
    }
    
    
    
    
    
    
}
