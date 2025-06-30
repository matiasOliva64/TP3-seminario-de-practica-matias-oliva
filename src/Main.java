import views.WindowLogin;
import views.WindowMain;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WindowLogin::new); //Ejecuta la ventana de login
    }
}