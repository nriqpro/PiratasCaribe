package piratascaribe;

public class maquina2 extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form maquina2
     */
    public maquina2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
                            
    private void initComponents() {

        PuertodeLaReina = new javax.swing.JLabel();
        BahiadelaEsperanza = new javax.swing.JLabel();
        CuevadelosMarineros = new javax.swing.JLabel();
        CuevadelBucanero = new javax.swing.JLabel();
        BahiadelBuenReposo = new javax.swing.JLabel();
        IslaLaHolandesa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        PuertodeLaReina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-puertoreina2.png"))); // NOI18N
        getContentPane().add(PuertodeLaReina);
        PuertodeLaReina.setBounds(590, 380, 130, 100);

        BahiadelaEsperanza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-esperanza.png"))); // NOI18N
        getContentPane().add(BahiadelaEsperanza);
        BahiadelaEsperanza.setBounds(180, 230, 90, 110);

        CuevadelosMarineros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-cuevamarinero.png"))); // NOI18N
        getContentPane().add(CuevadelosMarineros);
        CuevadelosMarineros.setBounds(480, 70, 100, 80);

        CuevadelBucanero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-cuevabucanero.png"))); // NOI18N
        getContentPane().add(CuevadelBucanero);
        CuevadelBucanero.setBounds(90, 450, 93, 90);

        BahiadelBuenReposo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/m2-reposo.png"))); // NOI18N
        getContentPane().add(BahiadelBuenReposo);
        BahiadelBuenReposo.setBounds(540, 230, 100, 80);

        IslaLaHolandesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/maquina2.png"))); // NOI18N
        getContentPane().add(IslaLaHolandesa);
        IslaLaHolandesa.setBounds(0, 0, 800, 600);

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
   /* public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new maquina2().setVisible(true);
        });
    }*/

    // Variables declaration - do not modify                     
    private javax.swing.JLabel IslaLaHolandesa;
    private javax.swing.JLabel PuertodeLaReina;
    private javax.swing.JLabel BahiadelaEsperanza;
    private javax.swing.JLabel CuevadelosMarineros;
    private javax.swing.JLabel CuevadelBucanero;
    private javax.swing.JLabel BahiadelBuenReposo;
    // End of variables declaration                   
}
