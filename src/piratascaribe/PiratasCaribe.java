/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PiratasCaribe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> ejemplo = new ArrayList<String>();
        ejemplo.add("Uno");
        System.out.println("Hola mundo piratas "+ejemplo.get(0));
        Calamidad c = new Calamidad("Crak",new Float(0.1),-10,-10,-10);
        for (int i = 0 ; i < 10 ; i++){
            if (c.ocurreCalamidad())
                System.out.println("Calamidad: "+c.getNombre()+"ha ocurrido");
            else
                System.out.println("No ha ocurido nada");
        }
    }
    
}
//comentario