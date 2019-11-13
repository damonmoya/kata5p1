package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    
    public static void main(String[] args) {
        String URL_BD_SQLite = new String("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\DB_SQLite\\Prueba.db");
        Connection connection = connect(URL_BD_SQLite);
    }

    private static Connection connect(String URL_BD_SQLite) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de datos conectada...");
            selectData_PEOPLE(connection);
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
        return connection;
    }
    
    private static void selectData_PEOPLE(Connection connection) {
        String SQL = "SELECT * FROM PEOPLE";
        try{
           Statement statement = connection.createStatement();
           ResultSet resultset = statement.executeQuery(SQL); 
           System.out.println("Id \t Nombre \t Apellidos \t Departamento");
           while (resultset.next()) {
               System.out.println( 
                       resultset.getInt("Id") + " \t " + 
                       resultset.getString("Name") + " \t \t " +
                       resultset.getString("Apellidos") + " \t " +
                       resultset.getString("Departamento"));
           }
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5: (SQLException)" + exception.getMessage());
        }
        
    }
    
}
