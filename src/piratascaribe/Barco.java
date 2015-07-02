/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Barco extends UnicastRemoteObject implements InterfazBarco{
    private String nombre;
    private Boolean pirata;
    private Integer nTripulacion;
    private Integer nRaciones;
    private Integer nAmmo;
    private Integer nTripulacionOriginal;
    private Integer nRacionesOriginal;
    private Integer nAmmoOriginal;
    private String maquinaOrigen;
    public  boolean enRetirada;
    private String maquinaAnterior;
    private String maquinaActual;
  //  private ArrayList<Mapa> mapas;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    
    private Cofre cofre;
    private String puertoOrigen;
    private Mapa mapaOrigen;
    //private ArrayList<Kapa> mapas;
    
    public Barco() throws RemoteException{
        
    }

    public Barco(String nombre, Boolean pirata, Integer nTripulacionOriginal, Integer nRacionesOriginal, Integer nAmmoOriginal) throws RemoteException{
        this.nombre = nombre;
        this.pirata = pirata;
        this.nTripulacionOriginal = nTripulacionOriginal;
        this.nRacionesOriginal = nRacionesOriginal;
        this.nAmmoOriginal = nAmmoOriginal;
        this.nAmmo =nAmmoOriginal;
        this.nTripulacion = nTripulacionOriginal;
        this.nRaciones = nRacionesOriginal;
        this.enRetirada=false;
//        this.mapas = new ArrayList<Mapa>();
        if (pirata){
            //crear cofree con capacidad 100
            this.cofre = new Cofre(100);
        }
        else
            this.cofre = new Cofre(50);
                
    }
    @Override
    public Mapa getMapaOrigen() {
        return mapaOrigen;
    }

    public void setMapaOrigen(Mapa mapaOrigen) {
        this.mapaOrigen = mapaOrigen;
    }

    
    
    public void setMaquinaOrigen(String maquinaOrigen) {
        this.maquinaOrigen = maquinaOrigen;
    }

    public void setPuertoOrigen(String puertoOrigen) {
        this.puertoOrigen = puertoOrigen;
    }
    

    public void imprimirContenido(){
        System.out.println ("--------------CONTENIDO BARCO--------------");
        System.out.println ("Numero Tripulacion: "+ this.nTripulacion);
        System.out.println ("Numero Raciones: "+ this.nRaciones);
        System.out.println ("Numero Ammo: "+ this.nAmmo);
        System.out.println ("--------------***************--------------");
    }
    @Override
    public void imprimirCofre() throws RemoteException{
        
       
        //codigo del metodo
        if (cofre!=null){
            if ( cofre.getTesoros()!=null){
                if (cofre.getTesoros().size() == 0){
                    System.out.println("El cofre no posee ningun tesoro :(");
                }
                else{
                    System.out.println("----------TESOROS----------");
                    System.out.println("Nombre \t\t Peso");
                    for (int i = 0 ; i < cofre.getTesoros().size() ; i++)
                        System.out.println(cofre.getTesoros().get(i).getNombre()+" -> "+cofre.getTesoros().get(i).getPeso());

                }
            }
            if (cofre.getMapas()!=null){
                if (cofre.getMapas().size() == 0){
                    System.out.println("El cofre no posee ningun mapa :(");
                }
                 else{
                    System.out.println("-----------MAPAS-----------");
                    System.out.println("Destino \t\t Estado");
                    for (int i = 0 ; i < cofre.getMapas().size() ; i++){
                        Mapa mapa = cofre.getMapas().get(i);
                        String dest;
                        if (mapa.esIsla()){
                            dest = mapa.getNombreIsla();
                            dest = dest +"/"+ mapa.getNombreSitio();
                        }else{
                            dest = mapa.getNombreCayo();
                        }
                             
                        System.out.println("Destino :" + dest + "->" + mapa.getEstado() );
                    }
                       

                }
            }
        }
        else{
            System.out.println("Error en Barco: imprimirCofre el cofre  es null");
        }
        
    }

    
    public String getName() {
        return nombre;
    }
    @Override
    public Integer getnTripulacion() throws RemoteException {
        return nTripulacion;
    }

    public Integer getnRaciones() throws RemoteException {
        return nRaciones;
    }

    public void setnRaciones(Integer nRaciones) throws RemoteException{
        this.nRaciones = nRaciones;
    }

    
    public void setnTripulacion(Integer nTripulacion)throws RemoteException {
        this.nTripulacion = nTripulacion;
    }

    public Integer getnAmmo() throws RemoteException {
        return nAmmo;
    }

    public void setnAmmo(Integer nAmmo) throws RemoteException{
        this.nAmmo = nAmmo;
    }

    public Integer getnTripulacionOriginal() {
        return nTripulacionOriginal;
    }

    public void setnTripulacionOriginal(Integer nTripulacionOriginal) {
        this.nTripulacionOriginal = nTripulacionOriginal;
    }

    public String getMaquinaAnterior() {
        return maquinaAnterior;
    }

    public void setMaquinaAnterior(String maquinaAnterior) {
        this.maquinaAnterior = maquinaAnterior;
    }

    public String getMaquinaActual() {
        return maquinaActual;
    }

    public void setMaquinaActual(String maquinaActual) {
        this.maquinaActual = maquinaActual;
    }

  
    
    
    
    public Cofre getCofre(){
        return this.cofre;
    }
    
    public ArrayList<Mapa> getMapas(){
        return cofre.getMapas();
    }
    
    public int agregarMapa(Mapa mapa){
        if (cofre!=null && cofre.getMapas()!=null){
            cofre.agregarMapa(mapa);
            return 1;
        }
        else{
            System.out.println("Error barco:  agregarMapa el arreglo de mapas es Null");
            return -1;
        }
    }
    
    @Override
    public int getSiguienteDestino() throws RemoteException{
       // return "No Implementado";
        for (int i = 0 ; i < cofre.getMapas().size() ; i++){
            if (cofre.getMapas().get(i).getEstado().equalsIgnoreCase(no_visitado))
                    return i;
        }
        return -1;
    }
    
    @Override
    public int marcarMapa(int i) throws RemoteException{
       // return "No Implementado";
        
        if (i < cofre.getMapas().size() ){
            cofre.getMapas().get(i).setEstado(visitado);
            return 1;
        }
        else {
            return -1;
        }

    }

    public Boolean getPirata() throws RemoteException{
        return pirata;
    }

    public Integer getnRacionesOriginal() throws RemoteException{
        return nRacionesOriginal;
    }

    public String getPuertoOrigen() throws RemoteException {
        return puertoOrigen;
    }

    public Integer getnAmmoOriginal() throws RemoteException{
        return nAmmoOriginal;
    }

    public String getMaquinaOrigen() throws RemoteException {
        return maquinaOrigen;
    }
    
    
    @Override
    public void partir() throws RemoteException{
      /*  String ipServer = "192.168.1.102";
        int puertoServer = 8000;
        String urlServer = "rmi://"+ipServer+":"+puertoServer+"/";*/
       //InterfazMaquina machine;
       //Thread.sleep((long) (10 * 1000.0));
        try {
            String maquinaAct = this.maquinaActual;
            GestorRMI g = new  GestorRMI();
            String maquinaSiguiente = cofre.getMapas().get(this.getSiguienteDestino()).getNombreMaquina();
            System.out.println("Maquina Siguiente : " + maquinaSiguiente + "Mapas sitio "+ cofre.getMapas().get(getSiguienteDestino()).getNombreSitio()+ "Cayo " + cofre.getMapas().get(getSiguienteDestino()).getNombreCayo());
            Thread.sleep((long) (3 * 1000.0));
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(maquinaSiguiente),g.getPuerto(maquinaSiguiente));
            InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(maquinaSiguiente);
            System.out.println("nombre maquina "+m.getNombre());
         /*   this.nTripulacion = nTripulacion - 10;
            this.nRaciones = nRaciones - 10;
            this.nAmmo = nAmmo - 10;*/
            m.recibirBarco(this.getName(),maquinaActual);
            
            
            
        } catch (Exception e) {
            System.out.println("Error en Barco : partir()");
            e.printStackTrace();
        }
     
     
    }
    
    @Override
    public void partirOrigen() throws RemoteException{
      /*  String ipServer = "192.168.1.102";
        int puertoServer = 8000;
        String urlServer = "rmi://"+ipServer+":"+puertoServer+"/";*/
       //InterfazMaquina machine;
       //Thread.sleep((long) (10 * 1000.0));
        try {
            String maquinaAct = this.maquinaActual;
            GestorRMI g = new  GestorRMI();
            String maquinaSiguiente = this.mapaOrigen.getNombreMaquina();
            System.out.println("Maquina Siguiente : " + maquinaSiguiente + "Mapas sitio "+ mapaOrigen.getNombreSitio()+ "Cayo " + mapaOrigen.getNombreCayo());
            Thread.sleep((long) (5 * 1000.0));
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(maquinaSiguiente),g.getPuerto(maquinaSiguiente));
            InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(maquinaSiguiente);
            System.out.println("nombre maquina " + m.getNombre());
            m.recibirBarco(this.getName(),maquinaActual);
            
            
            
        } catch (Exception e) {
            System.out.println("Error en Barco : partir()");
            e.printStackTrace();
        }
     
     
    }
    
    public void copiarBarco (InterfazBarco barco) throws RemoteException{
        this.nombre = barco.getName();
        this.pirata = barco.getPirata();
        this.nTripulacion = barco.getnTripulacion();
        this.nRaciones = barco.getnRaciones();
        this.nAmmo = barco.getnAmmo();
        this.nTripulacionOriginal = barco.getnTripulacionOriginal();
        this.nRacionesOriginal = barco.getnRacionesOriginal();
        this.nAmmoOriginal = barco.getnAmmoOriginal();
        this.maquinaOrigen = barco.getMaquinaOrigen();
        this.mapaOrigen = barco.getMapaOrigen();

        this.maquinaAnterior = barco.getMaquinaAnterior();
        this.maquinaActual = barco.getMaquinaActual();
//        this.mapas = barco.getMapas();
        this.cofre = barco.getCofre();
        this.puertoOrigen = barco.getPuertoOrigen();
    }
    
    public void cargarCofre (Cofre cofreLugar){
        System.out.println("Me toca agarrar mis tesoros jijiji");
        int corazon = cofreLugar.poseeCorazon();
        if (corazon >= 0){///posee el corazon
            while (cofre.agregarTesoro(cofreLugar.getTesoros().get(corazon))==1){
               if (cofre.getMapas().size() > 0)
                   cofre.eliminarMapa(cofre.getMapas().size()-1);
            }
        }
        else{//no posee el corazon
            for (int i = 0 ; i < cofreLugar.getMapas().size() ; i++){
              if (cofre.agregarMapa(cofreLugar.getMapas().get(i)) == 1)
                  break;
              
            }
            for (int i = 0 ; i < cofre.getTesoros().size() ; i++){
                cofreLugar.getTesoros().add(cofre.getTesoros().get(i));
            }
            //aqui coloo todos los elementos de mi cofre en el cofre del sitio, posteriormente vacio mi cofre
            //para luego tomar los objetos mas livianos
            cofreLugar.getTesoros().addAll(cofre.getTesoros());
            cofre.getTesoros().clear();
            
            while (!cofreLugar.getTesoros().isEmpty() && 
                    cofre.agregarTesoro(cofreLugar.getTesoros().get(cofreLugar.getTesoroMenorPeso()))!=1){
                cofreLugar.getTesoros().remove(cofreLugar.getTesoroMenorPeso());
            }
        }
    }
    
    
    
   
}
