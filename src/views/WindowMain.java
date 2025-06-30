package views;

import enums.FormMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowMain extends JFrame {


    private String TITLE_WINDOW = "Sistema integral de facturación y Stock";
    private final JMenuBar menuBar = new JMenuBar();
    //menus
    private final JMenu file = new JMenu("Archivo");
    private final JMenu help = new JMenu("Ayuda");

    //menus items
    private final JMenuItem exit = new JMenuItem("Salir");

    //tab panes para los paneles facturacion, clientes, etc.
    private final JTabbedPane tabbedPane = new JTabbedPane();

    public WindowMain(){
        init_components();
        props();
    }

    //inicializa la ventana
    private void props(){
        setTitle(TITLE_WINDOW);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    //inicializa los componentes
    private void init_components(){
        menuBar.add(file);
        menuBar.add(help);

        file.add(exit);
        setJMenuBar(menuBar);


        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        //agregar paneles al tab pane
        tabbedPane.addTab("Facturación",new PanelFacturacion());
        tabbedPane.addTab("Gestión de inventario",new PanelGestionStock());
        tabbedPane.addTab("Gestión de clientes", new PanelGestionClientes(this));
        tabbedPane.addTab("Gestión de usuarios",new PanelGestionUsuarios(this));

        add(tabbedPane,BorderLayout.CENTER);
    }


}
