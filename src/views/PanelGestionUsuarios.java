package views;

import controllers.UserController;
import enums.FormMode;
import models.users.Usuario;
import utilities.UserActionListerner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PanelGestionUsuarios extends JPanel implements UserActionListerner {

    String [] columns = {"ID","Nombre de usuario","Rol/Puesto"};
    private DefaultTableModel tableModel;
    private JTable tablaUsuarios;
    private final JButton registerUser = new JButton("Registrar usuario");
    private final JButton modifyUser = new JButton("Modificar usuario");
    private final JButton deleteUser = new JButton("Eliminar usuario");

    private final UserController userController;
    private final List<Usuario> currentUsers;

    public PanelGestionUsuarios(JFrame parentFrame){
        this.userController = new UserController();
        currentUsers = new ArrayList<>();
        setLayout(new BorderLayout());
        createPanelUsers();
    }

    private void createPanelUsers(){
        tableModel = new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        tablaUsuarios = new JTable(tableModel);
        tablaUsuarios.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane,BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panelButtons.setBackground(new Color(245,245,245));
        panelButtons.add(registerUser);
        panelButtons.add(modifyUser);
        panelButtons.add(deleteUser);
        registerUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionUsuarios.this);
                WindowRegisterUsers windowRegisterUsers = new WindowRegisterUsers(currentParentFrame, FormMode.REGISTER,null,PanelGestionUsuarios.this);
                windowRegisterUsers.setVisible(true);
            }
        });

        modifyUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = tablaUsuarios.getSelectedRow();
                if(selectedRow != -1){
                    int modelRow = tablaUsuarios.convertRowIndexToModel(selectedRow);
                    Usuario selectedUser = currentUsers.get(modelRow);

                    JFrame currentParentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelGestionUsuarios.this);
                    WindowRegisterUsers windowRegisterUsers = new WindowRegisterUsers(
                            currentParentFrame,
                            FormMode.MODIFY,
                            selectedUser,
                            PanelGestionUsuarios.this
                    );
                    windowRegisterUsers.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(PanelGestionUsuarios.this,"Por favor, selecciona un usuario de la tabla para modificar.",
                            "sin selección",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = tablaUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    int modelRow = tablaUsuarios.convertRowIndexToModel(selectedRow);
                    Usuario userToDelete = currentUsers.get(modelRow);
                    int confirm = JOptionPane.showConfirmDialog(
                            PanelGestionUsuarios.this,
                            "¿Estás seguro de que quieres eliminar al usuario " + userToDelete.getUserName() + " (ID: " + userToDelete.getId() + ")?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean success = userController.deleteUser(userToDelete.getId());
                        if (success) {
                            JOptionPane.showMessageDialog(PanelGestionUsuarios.this,
                                    "Usuario eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            onUserDeleted(); // Notifica la eliminación y refresca
                        } else {
                            JOptionPane.showMessageDialog(PanelGestionUsuarios.this,
                                    "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(PanelGestionUsuarios.this,
                            "Por favor, selecciona un usuario de la tabla para eliminar.",
                            "Sin Selección",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        add(panelButtons,BorderLayout.SOUTH);
        loadUsers();
    }

    private void loadUsers() {
        tableModel.setRowCount(0); // Limpia la tabla
        currentUsers.clear(); // Limpia la lista interna

        List<Usuario> users = userController.getAllUsers();
        if (users != null && !users.isEmpty()) {
            currentUsers.addAll(users); // Agrega los usuarios a la lista
            for (models.users.Usuario user : users) {
                Object[] rowData = {user.getId(), user.getUserName(), user.getRol() != null ? user.getRol().getRolName() : "N/A"};
                tableModel.addRow(rowData);
            }
        } else {
            System.out.println("DEBUG: No se obtuvieron usuarios o la lista está vacía.");
        }
    }

    @Override
    public void onUserAdded() {
        loadUsers();
    }

    @Override
    public void onUserUpdated() {
        loadUsers();
    }

    @Override
    public void onUserDeleted() {
        loadUsers();
    }
}
