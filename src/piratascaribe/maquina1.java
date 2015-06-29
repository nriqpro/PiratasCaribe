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
 * @author luis
 */
public class maquina1 extends javax.swing.JFrame {
    
    
    private Map coordenadas = new HashMap<String,Coordenada>();
    
    
    public maquina1() {
        initComponents();
    }
                        
    private void initComponents() {
         this.setTitle("Maquina 1");
        PuertoReal = new javax.swing.JLabel();
        PlayadelasPerlas = new javax.swing.JLabel();
        CaladelosMartires = new javax.swing.JLabel();
        CayodelBuenViento = new javax.swing.JLabel();
        IslaNuevaEsperanzas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        PuertoReal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-puertoreal2.png"))); // NOI18N
        getContentPane().add(PuertoReal);
        
        
        
        //NombreSitio/Cayo.setBounds(x,y, alto, ancho)
        PuertoReal.setBounds(610, 310, 130, 90);
        
        coordenadas.put("Puerto Real", new Coordenada(610,310));

        PlayadelasPerlas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-perlas.png"))); // NOI18N
        getContentPane().add(PlayadelasPerlas);
        PlayadelasPerlas.setBounds(240, 40, 140, 110);
        coordenadas.put("Playa de las Perlas", new Coordenada(240,40));

        CaladelosMartires.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m1-martires.png"))); // NOI18N
        getContentPane().add(CaladelosMartires);
        CaladelosMartires.setBounds(450, 120, 80, 90);
        coordenadas.put("Cala de los Martires", new Coordenada(450,120));
        
        CayodelBuenViento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/CayodelBuenViento.png"))); // NOI18N
        getContentPane().add(CayodelBuenViento);
        CayodelBuenViento.setBounds(60, 330, 270, 230);
        coordenadas.put("Cayo del Buen Viento", new Coordenada(60,330));

        IslaNuevaEsperanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina1.png"))); // NOI18N
        getContentPane().add(IslaNuevaEsperanzas);
        IslaNuevaEsperanzas.setBounds(0, 0, 800, 600);
        

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(maquina1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(maquina1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(maquina1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(maquina1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
       // java.awt.EventQueue.invokeLater(() -> {
           // new maquina1().setVisible(true);
      //  });
    //}
    public Map getCoordenadas (){
        return coordenadas;
    }
    // Variables declaration - do not modify                     
    private javax.swing.JLabel IslaNuevaEsperanzas;
    private javax.swing.JLabel PuertoReal;
    private javax.swing.JLabel PlayadelasPerlas;
    private javax.swing.JLabel CaladelosMartires;
    private javax.swing.JLabel CayodelBuenViento;
    // End of variables declaration                   
}
