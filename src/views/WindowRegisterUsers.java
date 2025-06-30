package views;

import controllers.RolController;
import controllers.UserController;
import enums.FormMode;
import models.users.Rol;
import models.users.Usuario;
import utilities.UserActionListerner;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class WindowRegisterUsers extends JDialog {


    private final JLabel LBL_TITLE = new JLabel();
    private final JLabel lblUserName = new JLabel("Nombre de usuario:");
    private final JLabel lblRol = new JLabel("Rol/Puesto:");
    private final JComboBox<Rol> cbRol = new JComboBox<>();
    private final JLabel lblPassword = new JLabel("Contraseña:");
    private final JLabel lblConfirmPassword = new JLabel("Confirmar contraseña:");
    private final JTextField txtUserName = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JPasswordField txtConfirmPassword = new JPasswordField();
    private final JButton btnRegister = new JButton();
    private final JLabel lblMessageErrors = new JLabel();

    private final FormMode currentMode;

    //instancia a interface, rolcontroler, usercontroler y objeto de tipo usuario
    private final UserActionListerner listener;
    private final RolController rolController;
    private final UserController userController;
    private final Usuario userToModify;

    public WindowRegisterUsers(JFrame parentFrame, FormMode mode, Usuario userToModify, UserActionListerner listener) {
        super(parentFrame, Utilities.getTitleForMode(FormMode.REGISTER, "usuarios"), true);
        rolController = new RolController();
        userController = new UserController();
        this.listener = listener;
        this.userToModify = userToModify;
        loadRoles();
        this.currentMode = mode;
        initializeFieldsForMode();
        setLayout(new BorderLayout());
        init_components();
        props();
        pack();
        setLocationRelativeTo(parentFrame);
    }

    private void props() {
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void init_components() {
        switch (currentMode) {
            case REGISTER -> {
                LBL_TITLE.setText("Registro de usuarios");
                btnRegister.setText("Registrar usuario");
            }
            case MODIFY -> {
                LBL_TITLE.setText("Modificación de usuarios");
                btnRegister.setText("Modificar usuario");
                lblPassword.setText("Nueva contraseña:");
                lblConfirmPassword.setText("Confirmar contraseña:");
            }
        }
        JPanel panelContainerTitle = new JPanel();
        LBL_TITLE.setFont(new Font("Serif", Font.BOLD, 24));
        LBL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
        panelContainerTitle.add(LBL_TITLE, BorderLayout.CENTER);
        panelContainerTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panelContainerTitle, BorderLayout.NORTH);

        JPanel panelContainerForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelContainerForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lblMessageErrors.setFont(new Font("Serif", Font.ITALIC, 12));
        lblMessageErrors.setForeground(Color.RED);
        panelContainerForm.add(lblUserName);
        panelContainerForm.add(txtUserName);
        panelContainerForm.add(lblRol);
        panelContainerForm.add(cbRol);
        panelContainerForm.add(lblPassword);
        panelContainerForm.add(txtPassword);
        panelContainerForm.add(lblConfirmPassword);
        panelContainerForm.add(txtConfirmPassword);
        panelContainerForm.add(lblMessageErrors);
        add(panelContainerForm, BorderLayout.CENTER);

        JPanel panelContainerButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelContainerButton.add(btnRegister);
        panelContainerButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        add(panelContainerButton, BorderLayout.SOUTH);

        //logica para registrar un usuario
        // la variable currentmode sirve para mostrar la misma ventana pero con el contenido de registrar o modificar usuarios
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (currentMode) {
                    case REGISTER -> {
                        String userName = txtUserName.getText();
                        String password = new String(txtPassword.getPassword());
                        String confirmPassword = new String(txtConfirmPassword.getPassword());

                        // Obtener el objeto Rol completo del JComboBox
                        Rol selectedRol = (Rol) cbRol.getSelectedItem();
                        String rolName = (selectedRol != null) ? selectedRol.getRolName() : null;

                        boolean success = false;
                        String successMessage = "";
                        String confirmMessage = "";
                        String dialogTitle = "";

                        confirmMessage = "¿Estás seguro de que quieres registrar este usuario?";
                        dialogTitle = "Confirmar Registro";

                        // >>> AQUI AGREGAMOS LA CONFIRMACION ANTES DE INTENTAR GUARDAR <<<
                        int confirm = JOptionPane.showConfirmDialog(
                                WindowRegisterUsers.this,
                                confirmMessage,
                                dialogTitle,
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            success = userController.registerUser(userName, password, confirmPassword, rolName); // Aqui se registra el usuario en la base de datos
                            successMessage = "Usuario registrado exitosamente.";

                            if (success) {
                                JOptionPane.showMessageDialog(WindowRegisterUsers.this, successMessage, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                                // Notificar al listener y cerrar la ventana
                                if (listener != null) {
                                    listener.onUserAdded();
                                }
                                WindowRegisterUsers.this.dispose(); // Cierra la ventana
                            } else {
                                // El mensaje de error específico (contraseñas, etc.) debería venir del UserController
                                lblMessageErrors.setText("Error al procesar el usuario. Revise los datos.");
                            }
                        } // Fin del if (confirm == JOptionPane.YES_OPTION)
                    }
                    case MODIFY -> {
                        int userId = userToModify.getId();
                        String newUserName = txtUserName.getText();
                        String newPassword = new String(txtPassword.getPassword());
                        String newConfirmPassword = new String(txtConfirmPassword.getPassword());
                        String newRolName = Objects.requireNonNull(cbRol.getSelectedItem()).toString();

                        int confirm = JOptionPane.showConfirmDialog(
                                null,
                                "¿Estás seguro que deseas modificar el usuario: " + userToModify.getUserName() + " ID: " + userId + "?",
                                "Confirmar modificación",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            userController.updateUser(userId, newUserName, newPassword, newConfirmPassword, newRolName);
                            if (listener != null) {
                                listener.onUserUpdated();
                            }
                            WindowRegisterUsers.this.dispose();
                            JOptionPane.showMessageDialog(null, "¡Usuario modificado exitosamente!", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Modificación cancelada.", "Cancelado", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

    }

    //carga los roles al componente comboBox
    private void loadRoles() {
        cbRol.removeAllItems();
        List<Rol> roles = rolController.getAllRoles();
        for (Rol rol : roles) {
            cbRol.addItem(rol);
        }
        if (!roles.isEmpty()) {
            cbRol.setSelectedIndex(0);
        }
    }

    //la ventana de modificar usuario con el campo username relleno con el usario seleccionado para modificar
    public void initializeFieldsForMode() {
        if (currentMode == FormMode.MODIFY && userToModify != null) {
            txtUserName.setText(userToModify.getUserName());
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            if (userToModify.getRol() != null) {
                for (int i = 0; i < cbRol.getItemCount(); i++) {
                    Rol itemRol = cbRol.getItemAt(i);
                    if (itemRol.getId() == userToModify.getRol().getId()) {
                        cbRol.setSelectedItem(itemRol);
                        break;
                    }
                }
            }
        }

    }

}
