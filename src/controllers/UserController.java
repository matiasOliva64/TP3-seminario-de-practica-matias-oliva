package controllers;

import models.users.Rol;
import models.users.Usuario;
import models.users.UsuarioDAO;
import utilities.PasswordHasherManager;

import java.util.List;

public class UserController {

    private final UsuarioDAO usuarioDAO;
    private final RolController rolController;

    public UserController(){
        this.usuarioDAO = new UsuarioDAO();
        this.rolController = new RolController();
    }

    public List<Usuario> getAllUsers(){
        return usuarioDAO.findAllUsers();
    }

    public boolean registerUser(String userName, String plainTextPassword,String confirmPassword,String rolName){
        Rol selectedRol = rolController.getRolByName(rolName);
        if(plainTextPassword.equals(confirmPassword)){
            if(selectedRol == null){
                System.out.println("Error de registro: El rol seleccionado no es válido o no existe!");
                return false;
            }
            Usuario newUser = new Usuario(userName, PasswordHasherManager.hashPassword(plainTextPassword),selectedRol);
            try {
                usuarioDAO.insertUser(newUser);
                System.out.println("Usuario: " + userName + "registrado exitosamente!");
                return true;
            }catch (Exception e){
                System.out.println("Error al guardar el usuario  en la base de datos: " + e.getMessage());
                return false;
            }
        }else{
            System.out.println("Las contraseñas ingresadas no coinciden!");
            return false;
        }
    }

    public boolean updateUser(int userId, String newUserName, String newPlainTextPassword,String confirmNewPassword,String newRolName){
        Usuario existingUser = usuarioDAO.findUserById(userId);
        if (existingUser == null){
            System.out.println("Error de actualización: Usuario con ID " + userId + " no encontrado.");
            return false;
        }
        if(newUserName == null || newUserName.trim().isEmpty() || newRolName == null || newRolName.trim().isEmpty()){
            System.out.println("Error de actualización: Nombre de usuario y Rol son obligatorios!");
            return false;
        }
        String passwordToSave = existingUser.getPassword();
        if(newPlainTextPassword != null && !newPlainTextPassword.trim().isEmpty()){
            if(!newPlainTextPassword.equals(confirmNewPassword)){
                System.out.println("Error de actualización: Las contraseñas no coinciden.");
                return false;
            }
            passwordToSave = PasswordHasherManager.hashPassword(newPlainTextPassword);
        }
        //Obtener el objeto Rol completo (con ID) para el nuevo rol
        Rol selectedRol = rolController.getRolByName(newRolName);
        if (selectedRol == null){
            System.out.println("Error de actualización: El rol seleccionado " + newRolName + " no es válido o no existe.");
            return false;
        }
        //Actualizar las propiedades del objeto Usuario existente
        existingUser.setUserName(newUserName);
        existingUser.setPassword(passwordToSave);
        existingUser.setRol(selectedRol);
        //Llamar al DAO para persistir los cambios
        try {
            usuarioDAO.updateUser(existingUser);
            System.out.println("Usuario " + newUserName + " (ID: " + userId + " )");
            return false;
        }catch (Exception e){
            System.out.println("Error al actualizar el usuario  en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        try {
            usuarioDAO.deleteUser(userId);
            System.out.println("Usuario con ID " + userId + " eliminado exitosamente.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario en el controlador: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
