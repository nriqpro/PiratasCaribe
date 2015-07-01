/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class Maquina extends UnicastRemoteObject implements InterfazMaquina {

    private Integer id;
    private ArrayList<Isla> islas;
    private ArrayList<Cayo> cayos;
    private String nombre;
    private Integer numPuertoRMI;
    private Map<String, String> nodos;
    private static String visitado = "visitado";
    private static String no_visitado = "no_visitado";
    private static String actual = "actual";
    private static String siguiente = "siguiente";
    private ArrayList<ArrayList<String>> maquinas;
    private Object maquinaInterfaz;

    public void setIslas(ArrayList<Isla> islas) {
        this.islas = islas;
    }

    public void setCayos(ArrayList<Cayo> cayos) {
        this.cayos = cayos;
    }

    public Maquina(int id, Integer numPuertoRMI) throws RemoteException {

        this.id = id;
        this.nombre = "maquina" + id;
        this.numPuertoRMI = numPuertoRMI;
        this.islas = new ArrayList<Isla>();
        this.cayos = new ArrayList<Cayo>();
        this.nodos = new HashMap<String, String>();
        this.maquinas = new ArrayList<ArrayList<String>>();

        Cofre cof = new Cofre(10000);

        Calamidad c = new Calamidad("Kraken", 1.0, 10, 10, 10);
        switch (id) {
            case 1:
                maquinaInterfaz = new maquina1();
                ((maquina1) maquinaInterfaz).setVisible(true);
                break;
            case 2:
                maquinaInterfaz = new maquina2();
                ((maquina2) maquinaInterfaz).setVisible(true);
                break;
              case 3: maquinaInterfaz = new maquina3(); ((maquina3)maquinaInterfaz).setVisible(true);break;
              case 4: maquinaInterfaz = new maquina4(); ((maquina4)maquinaInterfaz).setVisible(true);break;
        }

        /* Isla is = new Isla("Isla1");
         is.addSitio(new Sitio("Sitio1",cof,c));
         is.addSitio(new Sitio("Sitio2",cof,c));
         islas.add(is);*/

        nodos.put("maquina1", "localhost");
        nodos.put("maquina2", "localhost");
        nodos.put("maquina3", "localhost");
        nodos.put("maquina4", "localhost");
        nodos.put("servidor", "localhost");

    }

    public ArrayList<Isla> getIslas() {
        return islas;
    }

    public ArrayList<Cayo> getCayos() {
        return cayos;
    }

    @Override
    public String getNombre() throws RemoteException {
        return this.nombre;
    }

    @Override
    public void recibirBarco(String nombreBarco, String nombreMaquinaAnterior) throws RemoteException {
        try {
            GestorRMI g = new GestorRMI();
            String urlServer = "rmi://" + g.getIp(nombreMaquinaAnterior) + ":" + g.getPuerto(nombreMaquinaAnterior) + "/";
            System.out.println("He recibido el barco: " + nombreBarco + " en mis Aguas");
            //String urlObjeto =urlServer + nombreBarco; 
            //Ubico la referencia en el registro remoto
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(nombreMaquinaAnterior), g.getPuerto(nombreMaquinaAnterior));
            InterfazBarco barco = (InterfazBarco) registroRemoto.lookup(nombreBarco);

            //Añado el objeto a mi registro local
            Registry registroLocal = LocateRegistry.getRegistry(g.getPuerto(nombre));
            Barco b = new Barco();
            b.copiarBarco(barco);
            registroLocal.rebind(b.getName(), b);

            //Elimino la referencia en la maquina anterior
          /* InterfazMaquina m = (InterfazMaquina) registroRemoto.lookup(nombreMaquinaAnterior);
             m.eliminarReferenciaBarco(nombreBarco, nombreMaquinaAnterior);*/
            //hacer aqui procedimiento para dibujar interfaz barco moviendose a destino
            b.setMaquinaActual(this.nombre);
            b.setMaquinaAnterior(nombreMaquinaAnterior);
            ubicarBarco(b);
//           barco.marcarMapa()

            izarVelas(b.getName());
            System.out.println("El barco ha partido");

            //  System.out.println("Siguiente destino:"+barco.getSiguienteDestino());
        } catch (Exception e) {
            System.out.println("Error en Maquina: recibirBarco()");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarReferenciaBarco(String nombreBarco, String nombreMaquinaAnterior) throws RemoteException {
        try {
            GestorRMI g = new GestorRMI();
            Registry registroRemoto = LocateRegistry.getRegistry(g.getIp(nombreMaquinaAnterior), g.getPuerto(nombreMaquinaAnterior));
            registroRemoto.unbind(nombreBarco);
        } catch (Exception e) {
            System.out.println("Error Maquina: eliminarReferenciaBarco ");
            e.printStackTrace();
        }
    }

    public String getNombreMaquina() {
        return this.nombre;
    }

    public String getDireccionMaquina(String maquina) {
        return this.nodos.get(maquina);
    }

    public void addIsla(Isla isla) {
        if (this.islas != null && isla != null) {
            this.islas.add(isla);
        } else {
            System.out.println("Error en Maquina: addIsla 'islas' o 'isla' es null");
        }
    }

    public void addCayo(Cayo cayo) {
        if (this.cayos != null && cayo != null) {
            this.cayos.add(cayo);
        } else {
            System.out.println("Error en Maquina: addCayo 'cayos' o 'cayo' es null");
        }
    }

    public void izarVelas(String nombreBarco) throws RemoteException {
        System.out.println("Debo izarVelas: " + nombreBarco);
        for (int i = 0; i < islas.size(); i++) {
            for (int j = 0; j < islas.get(i).getSitios().size(); j++) {

                for (int k = 0; islas.get(i).getSitios().get(j).getBarcos() != null && k < islas.get(i).getSitios().get(j).getBarcos().size(); k++) {

                    if (islas.get(i).getSitios().get(j).getBarcos().get(k).getName().equalsIgnoreCase(nombreBarco)) {
                        Barco barco = islas.get(i).getSitios().get(j).getBarcos().get(k);
                        islas.get(i).getSitios().get(j).getBarcos().remove(k);
                        int x = barco.getSiguienteDestino();//ACOMODAR
                        if (x >= 0) {
                            System.out.println("Siguiente destino:" + barco.getMapas().get(x).getNombreIsla());
                            System.out.println("partir");
                            barco.partir();
                        } else if (barco.getMapaOrigen().getNombreMaquina().equalsIgnoreCase(this.nombre)) {
                            System.out.println("He retornado a mi origen hacer algo...");


                        } else {
                            System.out.println("He visitado todos mis lugares, me regreso al inicio");
                            barco.partirOrigen();
                        }

                        return;
                    }
                }
            }
        }

        for (int i = 0; i < cayos.size(); i++) {
            for (int j = 0; cayos.get(i).getBarcos() != null && j < cayos.get(i).getBarcos().size(); j++) {
                if (cayos.get(i).getBarcos().get(j).getName().equalsIgnoreCase(nombreBarco)) {
                    /*System.out.println("En el cayo "+ cayos.get(i).getNombre());
                     System.out.println("\t barco: "+ cayos.get(i).getBarcos().get(j).getName());
                     cayos.get(i).getBarcos().remove(j);*/

                    Barco barco = cayos.get(i).getBarcos().get(j);

                    int x = barco.getSiguienteDestino();
                    if (x >= 0) {
                        System.out.println("Siguiente destino:" + barco.getMapas().get(x).getNombreIsla());//ACOMODAR NO SOLO ISLA
                        System.out.println("partir");
                        barco.partir();
                    } else {
                        System.out.println("He visitado todos mis lugares, me regreso al inicio");
                    }

                    cayos.get(i).getBarcos().remove(j);
                    return;
                }

            }
        }
        //System.out.println();
    }

    public void ubicarBarco(Barco barco) throws RemoteException {
        try {
            int sigDest = barco.getSiguienteDestino();
            Mapa mapa;

            /*System.out.println("El siguiente destino que tiene el barco es:" + barco.getMapas().get(sigDest).getNombreSitio());
             for (int i = 0 ; i < islas.size() ; i++){
             for (int j = 0 ; j < islas.get(i).getSitios().size() ; j++){
             System.out.println(islas.get(i).getNombre()+"/"+islas.get(i).getSitios().get(j).getNombre());
             }
             }*/
            //System.out.println("Debo ubicarlo en ");
            if (sigDest >= 0) {//encontro destino con exito
                barco.marcarMapa(sigDest);//marcamos el sitio donde estabamos anteriormente como visitado
                mapa = barco.getMapas().get(sigDest); //marcamos el mapa en el sitio como actual
                mapa.setEstado("actual");
                System.out.println("Entro en sigDest");
                if (mapa.esIsla() && islas != null) {
                    System.out.println("Entro en mapa es isla y tod eso");
                    for (int i = 0; i < islas.size(); i++) {
                        if (islas.get(i).getNombre().equalsIgnoreCase(mapa.getNombreIsla())) {
                            System.out.print(i);
                            ArrayList<Sitio> sitios;
                            sitios = islas.get(i).getSitios();
                            for (int j = 0; j < sitios.size(); j++) {
                                if (sitios.get(j).getNombre().equalsIgnoreCase(mapa.getNombreSitio())) {

                                    System.out.println("Lo he ubicado en el sitio: " + sitios.get(j).getNombre());
                                    
                                    switch (id) {
                                    case 1:
                                        
                                      System.out.println("Que tiene como coordenadas X:" + 
                                             ((maquina1)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getX() + ", Y: "+
                                             ((maquina1)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getY() ); break;
                                    case 2:
                                        System.out.println("Que tiene como coordenadas X:" + 
                                             ((maquina2)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getX() + ", Y: "+
                                             ((maquina2)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getY() ); break;
                                      case 3:  System.out.println("Que tiene como coordenadas X:" + 
                                             ((maquina3)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getX() + ", Y: "+
                                             ((maquina3)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getY() ); break;
                                      case 4: System.out.println("Que tiene como coordenadas X:" + 
                                             ((maquina4)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getX() + ", Y: "+
                                             ((maquina4)maquinaInterfaz).getCoordenadas().get(sitios.get(j).getNombre()).getY() ); break;
                                }
                                    if (sitios.get(j).getBarcos() != null && sitios.get(j).getBarcos().size() >= 1) { //hay mas de dos barcos encallados 
                                        System.out.println("Se han encontado dos barcos en la maquina(isla): "
                                                + this.nombre);
                                        if (sitios.get(j).getBarcos().size() == 3) {
                                            if (sitios.get(j).getBarcos().get(0).enRetirada != true) {
                                                combate(sitios.get(j).getBarcos().get(0), sitios.get(j).getBarcos().get(2));
                                            }
                                            if (sitios.get(j).getBarcos().get(2).enRetirada != true && sitios.get(j).getBarcos().get(1).enRetirada != true) {
                                                combate(sitios.get(j).getBarcos().get(1), sitios.get(j).getBarcos().get(2));
                                            }
                                        } else {
                                            if (sitios.get(j).getBarcos().get(0).enRetirada != true) {
                                                combate(sitios.get(j).getBarcos().get(0), sitios.get(j).getBarcos().get(1));
                                            }
                                        }

                                        //verificar que faccion son
                                        //si son diferentes pelear
                                        //si sn 3 barcos pelear de una
                                    } else {//ocurre calamidad solo ocurre calamidad si hay un solo barco
                                        Calamidad calamidad = sitios.get(j).getCalamidad();
                                        if (calamidad != null && calamidad.ocurreCalamidad()) {//true ocurre calamidad
                                            System.out.println("Oh no! Ha ocurrido: " + calamidad.getNombre());
                                            System.out.println("ELEMENTOS ORIGINALES");
                                            System.out.println("Tripulacion: " + barco.getnTripulacion());
                                            System.out.println("Ammo: " + barco.getnAmmo());
                                            System.out.println("Raciones: " + barco.getnRaciones());
                                            barco.setnAmmo(barco.getnAmmo() - calamidad.getResta_ammo());
                                            barco.setnTripulacion(barco.getnTripulacion() - calamidad.getResta_trip());
                                            barco.setnRaciones(barco.getnRaciones() - calamidad.getResta_racion());
                                            System.out.println("ELEMENTOS LUEGO CALAMIDAD");
                                            System.out.println("Tripulacion: " + barco.getnTripulacion());
                                            System.out.println("Ammo: " + barco.getnAmmo());
                                            System.out.println("Raciones: " + barco.getnRaciones());

                                        }
                                    }
                                    sitios.get(j).encallaBarco(barco);
                                }
                            }
                        }
                    }

                } else {
                    int i;
                    for (i = 0; i < cayos.size(); i++) {
                        if (cayos.get(i).getNombre().equalsIgnoreCase(mapa.getNombreCayo())) {

                            System.out.println("Lo he ubicado en el cayo: " + cayos.get(i).getNombre());
                            if (cayos.get(i).getBarcos() != null && cayos.get(i).getBarcos().size() > 1) {
                                if (cayos.get(i).getBarcos().size() == 3) {
                                    if (cayos.get(i).getBarcos().get(0).enRetirada != true) {
                                        combate(cayos.get(i).getBarcos().get(0), cayos.get(i).getBarcos().get(2));
                                    }
                                    if (cayos.get(i).getBarcos().get(2).enRetirada != true && cayos.get(i).getBarcos().get(1).enRetirada != true) {
                                        combate(cayos.get(i).getBarcos().get(1), cayos.get(i).getBarcos().get(2));
                                    }
                                } else {
                                    combate(cayos.get(i).getBarcos().get(0), cayos.get(i).getBarcos().get(1));
                                }
                                System.out.println("Se han encontado dos barcos en la maquina(cayo): "
                                        + this.nombre + "IMPLEMENTAR CODIGO PELEA");
                            } else {//ocurre calamidad solo ocurre calamidad si hay un solo barco
                                Calamidad calamidad = cayos.get(i).getCalamidad();
                                if (calamidad != null && calamidad.ocurreCalamidad()) {//true ocurre calamidad
                                    System.out.println("Oh no! Ha ocurrido: " + calamidad.getNombre());
                                    System.out.println("ELEMENTOS ORIGINALES");
                                    System.out.println("Tripulacion: " + barco.getnTripulacion());
                                    System.out.println("Ammo: " + barco.getnAmmo());
                                    System.out.println("Raciones: " + barco.getnRaciones());
                                    barco.setnAmmo(barco.getnAmmo() - calamidad.getResta_ammo());
                                    barco.setnTripulacion(barco.getnTripulacion() - calamidad.getResta_trip());
                                    barco.setnRaciones(barco.getnRaciones() - calamidad.getResta_racion());
                                    System.out.println("ELEMENTOS LUEGO CALAMIDAD");
                                    System.out.println("Tripulacion: " + barco.getnTripulacion());
                                    System.out.println("Ammo: " + barco.getnAmmo());
                                    System.out.println("Raciones: " + barco.getnRaciones());

                                }
                            }
                            cayos.get(i).encallaBarco(barco);
                        }
                    }
                    if (i == cayos.size()) {
                        System.out.println("Error en Maquina: Nombre de Cayo no se logra ubicar en esta maquina");
                    }
                }

            } else { //no encontro destino
                if (barco.getMapaOrigen().esIsla()) {
                    for (int i = 0; islas != null && i < islas.size(); i++) {
                        if (islas.get(i).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreIsla())) {
                            for (int j = 0; islas.get(i).getSitios() != null && j < islas.get(i).getSitios().size(); j++) {
                                if (islas.get(i).getSitios().get(j).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreSitio())) {
                                    islas.get(i).getSitios().get(j).encallaBarco(barco);
                                    System.out.println("Lo he ubicado en sitio origen: " + islas.get(i).getSitios().get(j).getNombre());

                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; cayos != null && i < cayos.size(); i++) {
                        if (cayos.get(i).getNombre().equalsIgnoreCase(barco.getMapaOrigen().getNombreCayo())) {
                            cayos.get(i).encallaBarco(barco);
                            System.out.println("Lo he ubicado en el cayo origen: " + cayos.get(i).getNombre());

                        }
                    }

                }
                System.out.println("Error Maquina: ubicarBarco no se ha encontrado siguente destino");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void combate(Barco b1, Barco b2) throws RemoteException {
        if (b1.getPirata() == b2.getPirata()) {
            return;
        }
        if (2 * b1.getnTripulacion() < b2.getnTripulacion() || 2 * b1.getnAmmo() < b2.getnAmmo()) {
            System.out.println("El barco " + b1.getName() + " se ha retirado!");
            b1.enRetirada = true;
            if (b1.getSiguienteDestino() == -1) {
                b1.partirOrigen();
            } else {
                b1.partir();
            }
        } else {
            if (2 * b2.getnTripulacion() < b1.getnTripulacion() || 2 * b2.getnAmmo() < b1.getnAmmo()) {
                System.out.println("El barco " + b2.getName() + " se ha retirado!");
                b2.enRetirada = true;
                if (b2.getSiguienteDestino() == -1) {
                    b2.partirOrigen();
                } else {
                    b2.partir();
                }
            } else {
                int aux;
                if (b1.getnTripulacion() > b2.getnTripulacion()) {
                    aux = b1.getnTripulacion() - b2.getnTripulacion();
                } else {
                    aux = b2.getnTripulacion() - b1.getnTripulacion();
                }
                b1.setnTripulacion(b1.getnTripulacion() - (aux / 2));
                b2.setnTripulacion(b2.getnTripulacion() - (aux / 2));
                if (b1.getnAmmo() > b2.getnAmmo()) {
                    aux = b1.getnAmmo() - b2.getnAmmo();
                } else {
                    aux = b2.getnAmmo() - b1.getnAmmo();
                }
                b1.setnAmmo(b1.getnAmmo() - (aux / 2));
                b2.setnAmmo(b2.getnAmmo() - (aux / 2));
            }
            if (3 * b1.getnTripulacion() <= b1.getnTripulacionOriginal()) {
                b1.enRetirada = true;
                b1.partirOrigen();
            }
            if (3 * b2.getnTripulacion() <= b2.getnTripulacionOriginal()) {
                b2.enRetirada = true;
                b2.partirOrigen();
            }
            System.out.println("Combate Finalizado!");
        }
    }
}
