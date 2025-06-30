package views;

import enums.FormMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGestionClientes extends JPanel {

    //array y matriz usada para llenar una tabla en la interfaz GUI
    String [] columns = {"Nombre","Apellido","Dirección","Telefono"};
    Object[][] dataUsers = {{"Juan","Perez","calle falsa 123","351266566"}};

    //componentes de la ventana gestion de clientes
    private final JButton registerCustomer = new JButton("Registrar cliente");
    private final JButton modifyCustomer = new JButton("Modificar cliente");
    private final JButton deleteCustomer = new JButton("Eliminar cliente");


    //constructor
    public PanelGestionClientes(JFrame parentFrame){
        setLayout(new BorderLayout());
        createPanelClientes();
    }

    //metodo para colocar componentes en el panel
    private void createPanelClientes(){
        JTable tablaClientes = new JTable(dataUsers,columns);
        tablaClientes.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtons.setBackground(new Color(245,245,245));
        panelButtons.add(registerCustomer);
        panelButtons.add(modifyCustomer);
        panelButtons.add(deleteCustomer);

        //accion para el boton registrar clientes
        registerCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionClientes.this);
                WindowRegisterClientes windowRegisterClientes = new WindowRegisterClientes(currentParentFrame, FormMode.REGISTER);
            }
        });
        //accion para el boton modificar clientes
        modifyCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionClientes.this);
                WindowRegisterClientes windowRegisterClientes = new WindowRegisterClientes(currentParentFrame, FormMode.MODIFY);
            }
        });

        add(panelButtons,BorderLayout.SOUTH);


    }
}
