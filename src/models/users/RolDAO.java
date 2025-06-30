package models.users;

import utilities.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    //busca un rol por su nombre
    public Rol findRolByRolName(String rolName){
        String sql = "SELECT id, nombre FROM Rol WHERE nombre = ?";
        Rol rol = null;
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, rolName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nameRol = rs.getString("nombre");
                rol = new Rol(id,nameRol);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por nombre en DB: " + e.getMessage());
            e.printStackTrace();
        }
        return rol;
    }

    //devuelve todos los roles existentes
    public List<Rol> findAllRols(){
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT id, nombre FROM Rol";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                int id = rs.getInt("id");
                String nameRol = rs.getString("nombre");
                roles.add(new Rol(id,nameRol));
            }
        }catch (SQLException e){
            System.err.println("Error al obtener todos los roles: " + e.getMessage());
            e.printStackTrace();
        }
        return roles;
    }

    //inserte un nuevo rol
    public void  insertRol(Rol rol){
        String sql = "INSERT INTO Rol (nombre) VALUES (?)";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, rol.getRolName());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rol '" + rol.getRolName() + "' insertado. Filas afectadas: " + rowsAffected);

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // rol.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar rol: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
