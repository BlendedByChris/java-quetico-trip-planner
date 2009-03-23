package bcis3680_prob3;

/**
 * Driver
 *
 * Main driver class
 *
 * @author cleblanc
 */
public class Driver {

    /**
     * Main
     *
     * Shows the GUI
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new JFrameSplash().setVisible(true);
            }
        });
    }

}
