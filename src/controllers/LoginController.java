package controllers;

import models.users.Usuario;
import models.users.UsuarioDAO;
import utilities.PasswordHasherManager;

public class LoginController {

    //variable para instanciar un objeto de tipo UsuarioDAO
    private UsuarioDAO usuarioDAO;

    public LoginController(){
        this.usuarioDAO = new UsuarioDAO();
    }

    //Metodo usado para autenticar cuando un usuario se logea en el sistema
    public boolean authenticate(String userName, String plainTextPassword){
        Usuario usuario = usuarioDAO.findUserByUsername(userName);
        if(usuario == null){
            return false;
        }
        String storedHashedPassword = usuario.getPassword();
        return PasswordHasherManager.verifyPassword(plainTextPassword,storedHashedPassword);
    }

}
