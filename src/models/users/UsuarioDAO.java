package models.users;

import utilities.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    //busca un usuario por su nombre
    public Usuario findUserByUsername(String username) {
        String sql = "SELECT id, nombreUsuario, password, idRol FROM Usuario WHERE nombreUsuario = ?";
        Usuario user = null;
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("nombreUsuario");
                String dbHashedPassword = rs.getString("password");
                String rolNombre = rs.getString("idRol");
                Rol rol = (rolNombre != null) ? new Rol(rolNombre) : null;

                user = new Usuario(
                        id,
                        userName,
                        dbHashedPassword,
                        rol
                );
                // user.setId(rs.getInt("id"));
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por nombre en DB: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    //busca un usuario por su id
    public Usuario findUserById(int userId) {
        String sql = "SELECT u.id, u.nombreUsuario, u.password, r.id AS rolId, r.nombre AS rolNombre " +
                "FROM Usuario u JOIN Rol r ON u.idRol = r.id WHERE u.id = ?";

        Usuario user = null; // Inicializamos el usuario a null

        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, userId); // Establecemos el parámetro para la cláusula WHERE

            ResultSet rs = pstmt.executeQuery();

            // Si se encuentra una fila, construimos el objeto Usuario
            if (rs.next()) {
                int id = rs.getInt("id");
                String fetchedUsername = rs.getString("nombreUsuario");
                String hashedPassword = rs.getString("password");
                int rolId = rs.getInt("rolId");
                String rolNombre = rs.getString("rolNombre");
                Rol rol = new Rol(rolId, rolNombre); // Crea el objeto Rol completo

                user = new Usuario(id, fetchedUsername, hashedPassword, rol);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por ID en DB: " + e.getMessage());
            e.printStackTrace();
        }
        return user; // Devolvemos el usuario encontrado o null
    }

    // devuelve todos los usuarios existentes
    public List<Usuario> findAllUsers() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT u.id, u.nombreUsuario, u.password, r.id AS rolId, r.nombre AS rolNombre FROM Usuario u JOIN Rol r ON u.idRol = r.id;";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("nombreUsuario");
                String password = rs.getString("password");
                int rolId = rs.getInt("rolId");
                String rolNombre = rs.getString("rolNombre");
                Rol rol = new Rol(rolId,rolNombre);

                Usuario user = new Usuario(id,username,password, rol);
                usuarios.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los usuarios de la DB: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    //inserta un nuevo usuario
    public void insertUser(Usuario user) {
        String sql = "INSERT INTO Usuario (nombreUsuario, password, idRol) VALUES (?, ?, ?)";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            if(user.getRol() != null){
                pstmt.setInt(3,user.getRol().getId());
            }else{
                pstmt.setNull(3, Types.INTEGER);
            }
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Usuario '" + user.getUserName() + "' insertado. Filas afectadas: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //modifica un nuevo usuario
    public void updateUser(Usuario user){
        String sql = "UPDATE Usuario SET nombreUsuario = ?, password = ?, idRol = ? WHERE id = ?";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());

            if(user.getRol() != null){
                pstmt.setInt(3,user.getRol().getId());
            }else{
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setInt(4,user.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Usuario " + user.getUserName() + " actualizado. Filas afectadas: " + rowsAffected);
        }catch (SQLException e){
            System.out.println("Error al actualizar usuario: " +  e.getMessage());
            e.printStackTrace();
        }
    }

    //borra un usuario
    public void deleteUser(int userId){
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,userId);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Usuario con ID " + userId + " eliminado. Filas afectadas: " + rowsAffected);
        }catch (SQLException e){
            System.err.println("Error al eliminar usuario de la DB: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
