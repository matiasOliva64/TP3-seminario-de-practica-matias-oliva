package views;

import javax.swing.*;
import java.awt.*;

public class PanelFacturacion extends JPanel {

    //array y matriz usada para llenar una tabla en la interfaz GUI
    String [] columns = {"Código product","Nombre producto","Cantidad","Precio unitario"};
    Object[][] dataUsers = {{"12345678","Coca Cola 2L",2,4000.00}};

    //componentes de la ventana facturacion
    private JLabel lblAddProduct = new JLabel("Agregar Producto:");
    private JTextField txtAddProduct = new JTextField(20);
    private JLabel lblIva = new JLabel("IVA:");
    private JTextField txtIva = new JTextField(4);
    private JButton btnClearList = new JButton("Limpiar Lista");
    private JLabel lblSubTotal = new JLabel("Sub Total:");
    private JLabel lblSubTotalNum = new JLabel("$1000");
    private JLabel lblTotal = new JLabel("Total:");
    private JLabel lblTotalNum = new JLabel("$2400");
    private JButton btnEmitirFactura = new JButton("Emitir Factura");
    private JButton btnListarFacturas = new JButton("Listar Facturas");
    private Font fontStyleSubTotal = new Font("Serif",Font.BOLD,30);
    private Font fontStyleTotal = new Font("Serif",Font.BOLD,40);
    private JSeparator separatorLabels = new JSeparator();

    //constructor
    public PanelFacturacion(){
        setLayout(new BorderLayout());
        createPanelFacturacion();
    }

    //metodo para colocar los componentes en el panel
    private void createPanelFacturacion(){
        JTable tablaFacturacion = new JTable(dataUsers,columns);
        tablaFacturacion.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaFacturacion);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelContainerAddProduct = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelContainerAddProduct.add(lblAddProduct);
        panelContainerAddProduct.add(txtAddProduct);

        JPanel panelContainerSetIva = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelContainerSetIva.add(lblIva);
        panelContainerSetIva.add(txtIva);
        panelContainerSetIva.add(btnClearList);

        JPanel panelContainerSubTotal = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        lblSubTotal.setFont(fontStyleSubTotal);
        lblSubTotalNum.setFont(fontStyleSubTotal);
        panelContainerSubTotal.add(lblSubTotal);
        panelContainerSubTotal.add(lblSubTotalNum);

        JPanel panelContainerTotal = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        lblTotal.setFont(fontStyleTotal);
        lblTotalNum.setFont(fontStyleTotal);
        panelContainerTotal.add(lblTotal);
        panelContainerTotal.add(lblTotalNum);

        JPanel panelContainerSubTotalAndTotal = new JPanel();
        panelContainerSubTotalAndTotal.setLayout(new BoxLayout(panelContainerSubTotalAndTotal,BoxLayout.Y_AXIS));
        panelContainerSubTotalAndTotal.add(panelContainerSubTotal);
        panelContainerSubTotalAndTotal.add(panelContainerTotal);
        panelContainerSubTotalAndTotal.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        JPanel panelButtonsFacturas = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtonsFacturas.add(btnEmitirFactura);
        panelButtonsFacturas.add(btnListarFacturas);

        JPanel boxLayoutContainerTop = new JPanel();
        boxLayoutContainerTop.setLayout(new BoxLayout(boxLayoutContainerTop,BoxLayout.Y_AXIS));

        JPanel boxLayoutContainerBottom = new JPanel();
        boxLayoutContainerBottom.setLayout(new BoxLayout(boxLayoutContainerBottom,BoxLayout.Y_AXIS));

        boxLayoutContainerTop.add(panelContainerAddProduct);
        boxLayoutContainerTop.add(panelContainerSetIva);

        boxLayoutContainerBottom.add(panelContainerSubTotalAndTotal);
        boxLayoutContainerBottom.add(panelButtonsFacturas);

        JPanel rightSidePanel = new JPanel(new BorderLayout());
        rightSidePanel.add(boxLayoutContainerTop,BorderLayout.NORTH);
        rightSidePanel.add(boxLayoutContainerBottom,BorderLayout.SOUTH);
        rightSidePanel.add(Box.createVerticalStrut(20),BorderLayout.CENTER);

        add(rightSidePanel,BorderLayout.EAST);
    }
}
