package views;

import enums.FormMode;
import models.product.Marca;
import models.product.Proveedor;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowRegisterProducts extends JDialog {

    private final JLabel LBL_TITLE = new JLabel();
    private final JLabel lblNombre = new JLabel("Nombre:");
    private final JLabel lblCodigo= new JLabel("Código:");
    private final JLabel lblDescription = new JLabel("Descripción:");
    private final JLabel lblPrecio = new JLabel("Precio:");
    private final JLabel lblStockMinimo = new JLabel("Stock mínimo:");
    private final JLabel lblCantDisponible= new JLabel("Cantidad disponible:");
    private final JLabel lblMarca = new JLabel("Marca:");
    private final JLabel lblProveedor = new JLabel("Proveedor:");
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCodigo = new JTextField();
    private final JTextField txtDescription = new JTextField();
    private final JTextField txtPrecio = new JTextField();
    private final JSpinner spStockMinimo = new JSpinner();
    private final JSpinner spCantDisponible = new JSpinner();
    private final JComboBox<Marca> cbMarca = new JComboBox<>();
    private final JComboBox<Proveedor> cbProveedor = new JComboBox<>();
    private final JButton btnRegister = new JButton();
    private JLabel lblMessageErrors = new JLabel();

    private final FormMode currentMode;

    public WindowRegisterProducts(JFrame parentFrame,FormMode mode){
        super(parentFrame, Utilities.getTitleForMode(FormMode.REGISTER,"productos"),true);
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
                LBL_TITLE.setText("Registro de productos");
                btnRegister.setText("Registrar producto");
            }
            case MODIFY -> {
                LBL_TITLE.setText("Modificación de productos");
                btnRegister.setText("Modificar producto");
            }
        }
        JPanel panelContainerTitle = new JPanel();
        LBL_TITLE.setFont(new Font("Serif",Font.BOLD,24));
        LBL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
        panelContainerTitle.add(LBL_TITLE,BorderLayout.CENTER);
        panelContainerTitle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(panelContainerTitle,BorderLayout.NORTH);

        spStockMinimo.setModel(fillSpinners());
        spCantDisponible.setModel(fillSpinners());

        JPanel panelContainerForm = new JPanel(new GridLayout(9,2,10,10));
        panelContainerForm.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        lblMessageErrors.setFont(new Font("Serif",Font.ITALIC,12));
        lblMessageErrors.setForeground(Color.RED);
        panelContainerForm.add(lblNombre);
        panelContainerForm.add(txtNombre);
        panelContainerForm.add(lblCodigo);
        panelContainerForm.add(txtCodigo);
        panelContainerForm.add(lblDescription);
        panelContainerForm.add(txtDescription);
        panelContainerForm.add(lblPrecio);
        panelContainerForm.add(txtPrecio);
        panelContainerForm.add(lblStockMinimo);
        panelContainerForm.add(spStockMinimo);
        panelContainerForm.add(lblCantDisponible);
        panelContainerForm.add(spCantDisponible);
        panelContainerForm.add(lblMarca);
        panelContainerForm.add(cbMarca);
        panelContainerForm.add(lblProveedor);
        panelContainerForm.add(cbProveedor);

        add(panelContainerForm,BorderLayout.CENTER);

        JPanel panelContainerButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelContainerButton.add(btnRegister);
        panelContainerButton.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));
        add(panelContainerButton,BorderLayout.SOUTH);
    }

    private SpinnerListModel fillSpinners(){
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 1; i < 100;i++){
            values.add(i);
        }
        return new SpinnerListModel(values);
    }


}
