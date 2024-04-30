import GUI.*;
import javax.swing.SwingUtilities;

/** 
@author
* Sebastian Cruz
* Joaquín Fuenzalida
* Maximiliano Bustamante

**/

public class PROYECTOSIA1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });

    }
}
