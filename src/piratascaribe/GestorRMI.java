/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class GestorRMI {
    Map<String,String> ips;
    Map<String,Integer> puertos;
    
    public GestorRMI(){
        ips = new HashMap<String,String>();
        puertos =  new HashMap<String,Integer>();
        
        ips.put("server", "192.168.1.102");
        ips.put("maquina1", "192.168.1.102");
        ips.put("maquina2", "192.168.1.105");
        ips.put("maquina3", "192.168.1.102");
        ips.put("maquina4", "192.168.1.102");
        
        puertos.put("server", 8000);
        puertos.put("maquina1", 8001);
        puertos.put("maquina2", 8002);
        puertos.put("maquina3", 8003);
        puertos.put("maquina4", 8004);
    }
    
    public String getIp(String nombre){
        return ips.get(nombre);
    }
    
    public Integer getPuerto(String nombre){
        return puertos.get(nombre);
    }
    
}
