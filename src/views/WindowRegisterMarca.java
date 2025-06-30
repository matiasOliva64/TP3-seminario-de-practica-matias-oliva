package views;

import enums.FormMode;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class WindowRegisterMarca extends JDialog {

    private final JLabel LBL_TITLE = new JLabel("Registro de marcas");
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegister = new JButton("Registrar marca");
    private final JLabel lblMessageErrors = new JLabel();

    private final FormMode currentMode;

    public WindowRegisterMarca(JFrame parentFrame,FormMode mode){
        super(parentFrame, Utilities.getTitleForMode(FormMode.REGISTER,"marcas"),true);
        this.currentMode = mode;
        setLayout(new BorderLayout());
        init_components();
        props();
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void props() {
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void init_components() {
        switch (currentMode){
            case REGISTER -> {
                LBL_TITLE.setText("Registro de marcas");
                btnRegister.setText("Registrar marca");
            }
            case MODIFY -> {
                LBL_TITLE.setText("Modificación de marcas");
                btnRegister.setText("Modificar marca");
            }
        }
        JPanel panelContainerTitle = new JPanel();
        LBL_TITLE.setFont(new Font("Serif",Font.BOLD,24));
        LBL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
        panelContainerTitle.add(LBL_TITLE,BorderLayout.CENTER);
        panelContainerTitle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panelContainerTitle,BorderLayout.NORTH);

        JPanel panelContainerForm = new JPanel(new GridLayout(2,2,10,10));
        panelContainerForm.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        lblMessageErrors.setFont(new Font("Serif",Font.ITALIC,12));
        lblMessageErrors.setForeground(Color.RED);
        panelContainerForm.add(lblNombre);
        panelContainerForm.add(txtNombre);
        panelContainerForm.add(lblMessageErrors);

        add(panelContainerForm,BorderLayout.CENTER);

        JPanel panelContainerButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelContainerButton.add(btnRegister);
        panelContainerButton.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        add(panelContainerButton,BorderLayout.SOUTH);
    }
}
