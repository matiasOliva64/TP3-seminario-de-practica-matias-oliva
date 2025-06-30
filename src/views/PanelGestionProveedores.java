package views;

import enums.FormMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGestionProveedores extends JPanel {


    String [] columns = {"Nombre","Contacto","Dirección"};
    Object[][] dataUsers = {{"Distribuidora centro S.A","distribuidora@centro.com","calle falsa 5454"}};
    private final JButton registerProveedor = new JButton("Registrar proveedor");
    private final JButton modifyProveedor = new JButton("Modificar proveedor");
    private final JButton deleteProveedor = new JButton("Eliminar proveedor");

    public PanelGestionProveedores(){
        setLayout(new BorderLayout());
        createPanelProveedores();
    }

    private void createPanelProveedores() {
        JTable tablaProveedor = new JTable(dataUsers,columns);
        tablaProveedor.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaProveedor);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtons.setBackground(new Color(245,245,245));
        panelButtons.add(registerProveedor);
        panelButtons.add(modifyProveedor);
        panelButtons.add(deleteProveedor);

        registerProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionProveedores.this);
                WindowRegisterProveedor windowRegisterProveedor = new WindowRegisterProveedor(currentParentFrame, FormMode.REGISTER);
            }
        });

        modifyProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionProveedores.this);
                WindowRegisterProveedor windowRegisterProveedor = new WindowRegisterProveedor(currentParentFrame, FormMode.MODIFY);
            }
        });
        add(panelButtons,BorderLayout.SOUTH);
    }
}
