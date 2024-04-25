import GUI.MainFrame;

import javax.swing.SwingUtilities;

public class PROYECTOSIA1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true); // Hace visible la ventana principal
        });

    }
}
