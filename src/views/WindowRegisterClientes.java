package views;

import enums.FormMode;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

public class WindowRegisterClientes extends JDialog {

    private final JLabel LBL_TITLE = new JLabel();
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JLabel lblApellido = new JLabel("Apellido:");
    private final JLabel lblDireccion = new JLabel("Dirección:");
    private final JLabel lblTelefono = new JLabel("Telefono:");
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtDireccion = new JTextField();
    private final JTextField txtTelefono = new JTextField();
    private final JButton btnRegister = new JButton();
    private JLabel lblMessageErrors = new JLabel();

    private final FormMode currentMode;


    public WindowRegisterClientes(JFrame parentFrame,FormMode mode){
        super(parentFrame,Utilities.getTitleForMode(FormMode.REGISTER,"clientes"),true);
        this.currentMode = mode;
        setLayout(new BorderLayout());
        init_components();
        props();
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private void props(){
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void init_components(){
        switch (currentMode){
            case REGISTER -> {
                LBL_TITLE.setText("Registro de clientes");
                btnRegister.setText("Registrar cliente");
            }
            case MODIFY -> {
                LBL_TITLE.setText("Modificación de clientes");
                btnRegister.setText("Modificar cliente");
            }
        }
        JPanel panelContainerTitle = new JPanel();
        LBL_TITLE.setFont(new Font("Serif",Font.BOLD,24));
        LBL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
        panelContainerTitle.add(LBL_TITLE,BorderLayout.CENTER);
        panelContainerTitle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panelContainerTitle,BorderLayout.NORTH);

        JPanel panelContainerForm = new JPanel(new GridLayout(5,2,10,10));
        panelContainerForm.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        lblMessageErrors.setFont(new Font("Serif",Font.ITALIC,12));
        lblMessageErrors.setForeground(Color.RED);
        panelContainerForm.add(lblNombre);
        panelContainerForm.add(txtNombre);
        panelContainerForm.add(lblApellido);
        panelContainerForm.add(txtApellido);
        panelContainerForm.add(lblDireccion);
        panelContainerForm.add(txtDireccion);
        panelContainerForm.add(lblTelefono);
        panelContainerForm.add(txtTelefono);
        panelContainerForm.add(lblMessageErrors);
        add(panelContainerForm,BorderLayout.CENTER);

        JPanel panelContainerButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelContainerButton.add(btnRegister);
        panelContainerButton.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        add(panelContainerButton,BorderLayout.SOUTH);
    }

}
