package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String URL_BD_SQLite = new String("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\DB_SQLite\\Prueba.db");
        connect(URL_BD_SQLite);
    }

    private static void connect(String URL_BD_SQLite) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de datos conectada...");
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5::connect (SQLException)" + exception.getMessage());
        }
        finally{
            try{
                if (connection != null)
                    connection.close();
            }
            catch (SQLException exception) {
                System.out.println("ERROR Kata5::connect-finally (SQLException)" + exception.getMessage());
            }
        }
    }
    
}
