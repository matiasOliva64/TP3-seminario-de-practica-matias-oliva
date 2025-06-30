package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {

    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    //cargar los datos: URL, USERNAME y PASSWORD del archivo database.properties
    static {
        Properties properties = new Properties();
        try (InputStream input = DBconnection.class.getClassLoader().getResourceAsStream("database.properties")) {

            if (input == null) {
                System.err.println("Lo siento, no se pudo encontrar 'database.properties'. Asegúrate de que está en el classpath.");
            } else {
                properties.load(input);
                URL = properties.getProperty("db.url");
                USERNAME = properties.getProperty("db.username");
                PASSWORD = properties.getProperty("db.password");
                System.out.println("Configuración de BD cargada desde database.properties.");
            }
        } catch (IOException ex) {
            System.err.println("Error al cargar database.properties: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    //Entablar la conexion a la base de datos
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        System.out.println("Database connection established!");
        return connection;
    }

    public static void testConnection(){
        try (Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD)){
            System.out.println("Test connection successfully!");
        }catch (SQLException e){
            System.err.println("Test connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
