package views;

import controllers.LoginController;
import enums.FormMode;
import utilities.PasswordHasherManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class WindowLogin extends JFrame {

    //componentes de la ventana de login
    private String TITLE_WINDOW = "SIGFS - Login";
    private final JLabel titleLoginForm = new JLabel("Ingreso de usuarios");
    private final JLabel lblUserName = new JLabel("Usuario:");
    private final JLabel lblPassword = new JLabel("Contraseña:");
    private final TextField txtUserName = new TextField();
    private final JPasswordField pswPassword = new JPasswordField();
    private final JButton btnLogIn = new JButton("Ingresar");
    private final JLabel lblMessagesErrors = new JLabel();

    //Constructor
    public WindowLogin(){
        props();
        init_components();
    }

    //metodo para inicializar la ventana
    private void props(){
        setTitle(TITLE_WINDOW);
        setSize(new Dimension(300,400));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //metodo para inicializar los componentes de la ventana
    private void init_components() {
        titleLoginForm.setFont(new Font("Serif",Font.BOLD,24));
        titleLoginForm.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel para el título
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLoginForm, BorderLayout.CENTER);

        // Agregar el título en el norte de la ventana
        add(titlePanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Padding general
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        gbc.insets = new Insets(10, 10, 10, 10); // espacio entre componentes


        // Fila 0 - Label Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lblUserName, gbc);

        // Fila 0 - Campo Usuario
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        txtUserName.setPreferredSize(new Dimension(150, 25));
        centerPanel.add(txtUserName, gbc);

        // Fila 1 - Label Contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lblPassword, gbc);

        // Fila 1 - Campo Contraseña
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        pswPassword.setPreferredSize(new Dimension(150, 25));
        centerPanel.add(pswPassword, gbc);

        // Fila 2 - Botón
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        btnLogIn.setPreferredSize(new Dimension(100, 30));
        centerPanel.add(btnLogIn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        lblMessagesErrors.setFont(new Font("Serif",Font.ITALIC,12));
        lblMessagesErrors.setForeground(Color.RED);
        lblMessagesErrors.setPreferredSize(new Dimension(150,25));
        centerPanel.add(lblMessagesErrors,gbc);

        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userName = txtUserName.getText();
                String password = new String(pswPassword.getPassword());
                LoginController loginController = new LoginController();
                if(loginController.authenticate(userName,password)){
                    WindowLogin.this.dispose();
                    WindowMain windowMain = new WindowMain();
                }
                lblMessagesErrors.setText("Usuario y/o contraseña inválidos!");
            }
        });
        add(centerPanel, BorderLayout.CENTER);
    }

}
