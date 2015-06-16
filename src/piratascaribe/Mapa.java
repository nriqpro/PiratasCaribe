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
    private String estado; // visitado: ya visito el lugar y no debe volver, actual: esta situado actualmente en este lugar, 
                           //siguiente: lugar de destino, para que la maquina receptora pueda enviarlo a la ubicacon correspondiente (sitio/cayo).
                           //no visitado: lugar no visitado

    public Mapa(String maquina, String isla, String sitio, String cayo, Boolean tipoLugar) {
        this.maquina = maquina;
        this.isla = isla;
        this.sitio = sitio;
        this.cayo = cayo;
        this.tipoLugar = tipoLugar;
        this.estado = "no visitado";
    }
    
    public Boolean esIsla(){
        return tipoLugar;
    }

    public String getNombreMaquina() {
        return maquina;
    }

    public String getNombreIsla() {
        return isla;
    }

    public String getNombreSitio() {
        return sitio;
    }

    public String getNombreCayo() {
        return cayo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
