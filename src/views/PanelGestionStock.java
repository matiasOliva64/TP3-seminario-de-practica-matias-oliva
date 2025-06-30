package views;

import enums.FormMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGestionStock extends JPanel {

    String [] columns = {"Codigo","Nombre","Descripción","Categoria","Proveedor","Marca","Precio","Cantidad","Stock minimo"};
    Object[][] dataUsers = {{"12345678","Coca Cola 2L","Coca Cola 2L retornable","Bebidas","Distribuidora centro","Coca Cola",2000.00,10,10}};
    private final JButton registerProduct = new JButton("Registrar producto");
    private final JButton modifyProduct = new JButton("Modificar producto");
    private final JButton deleteProduct = new JButton("Eliminar producto");

    private final JTabbedPane subTabbedPane = new JTabbedPane();

    public PanelGestionStock(){
        setLayout(new BorderLayout());
        createPanelStock();
    }

    private void createPanelStock(){
        JPanel panelGestionProductos = new JPanel(new BorderLayout());

        JTable tablaProductos = new JTable(dataUsers,columns);
        tablaProductos.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        panelGestionProductos.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtons.setBackground(new Color(245,245,245));
        panelButtons.add(registerProduct);
        panelButtons.add(modifyProduct);
        panelButtons.add(deleteProduct);

        registerProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionStock.this);
                WindowRegisterProducts windowRegisterProducts = new WindowRegisterProducts(currentParentFrame, FormMode.REGISTER);
            }
        });

        modifyProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionStock.this);
                WindowRegisterProducts windowRegisterProducts = new WindowRegisterProducts(currentParentFrame, FormMode.MODIFY);
            }
        });

        subTabbedPane.addTab("Gestion de productos",panelGestionProductos);
        subTabbedPane.addTab("Gestion de marcas",new PanelGestionMarcas());
        subTabbedPane.addTab("Gestion de proveedores",new PanelGestionProveedores());

        add(subTabbedPane);
        panelGestionProductos.add(panelButtons,BorderLayout.SOUTH);

    }

}
