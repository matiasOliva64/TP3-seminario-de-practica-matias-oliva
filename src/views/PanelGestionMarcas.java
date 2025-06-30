package views;

import enums.FormMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGestionMarcas extends JPanel {

    String [] columns = {"Nombre"};
    Object[][] dataUsers = {{"Coca Cola"}};
    private final JButton registerMarca = new JButton("Registrar marca");
    private final JButton modifyMarca = new JButton("Modificar marca");
    private final JButton deleteMarca = new JButton("Eliminar marca");

    public PanelGestionMarcas(){
        setLayout(new BorderLayout());
        createPanelMarcas();
    }

    private void createPanelMarcas() {
        JTable tablaMarcas = new JTable(dataUsers,columns);
        tablaMarcas.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaMarcas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtons.setBackground(new Color(245,245,245));
        panelButtons.add(registerMarca);
        panelButtons.add(modifyMarca);
        panelButtons.add(deleteMarca);

        registerMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionMarcas.this);
                WindowRegisterMarca windowRegisterMarca = new WindowRegisterMarca(currentParentFrame, FormMode.REGISTER);
            }
        });

        modifyMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionMarcas.this);
                WindowRegisterMarca windowRegisterMarca = new WindowRegisterMarca(currentParentFrame, FormMode.MODIFY);
            }
        });

        add(panelButtons,BorderLayout.SOUTH);
    }
}
