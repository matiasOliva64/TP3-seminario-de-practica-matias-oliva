package controllers;

import models.users.Rol;
import models.users.RolDAO;

import java.util.List;

public class RolController {

    //instancia a RolDAO
    private final RolDAO rolDAO;

    public RolController(){
        this.rolDAO = new RolDAO();
    }

    //Devuelbe una lista de tipo Rol
    public List<Rol> getAllRoles(){
        return rolDAO.findAllRols();
    }

    //Devuelve un rol segun el nombre de rol pasado como parametro
    public Rol getRolByName(String rolName){
        return rolDAO.findRolByRolName(rolName);
    }
}
