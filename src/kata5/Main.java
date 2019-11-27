package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import kata5.view.MailListReader;

public class Main {
    
    public static void main(String[] args) {
        String URL_BD_SQLite = new String("jdbc:sqlite:C:\\Users\\Usuario\\Documents\\NetBeansProjects\\DB_SQLite\\KATA5.db");
        Connection connection = connect(URL_BD_SQLite);
    }

    private static Connection connect(String URL_BD_SQLite) {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de datos conectada...\n");
            selectData_PEOPLE(connection);
            
            //System.out.println("********");
            //createTable_email(connection);
            
            String fileName = new String("email.txt");
            List<String> mailList = MailListReader.read(fileName);
            
            System.out.println("********");
            insertEmails(connection, mailList);
            
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
           while (resultset.next()) {
               System.out.println( 
                       resultset.getInt("Id") + "\t" + 
                       resultset.getString("Name") + "\t" +
                       resultset.getString("Apellidos") + "\t" +
                       resultset.getString("Departamento"));
           }
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5: (SQLException)" + exception.getMessage());
        }  
    }
    
    private static void createTable_email(Connection connection) {
        String SQL = "CREATE TABLE IF NOT EXISTS direcc_email ( id integer PRIMARY KEY AUTOINCREMENT, direccion text NOT NULL);";

        try{
           Statement statement = connection.createStatement(); 
           statement.executeQuery(SQL);
           
           System.out.println("Tabla creada con éxito");

        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5_createTable: (SQLException) " + exception.getMessage());
        }  
    }
    
    private static void insertEmails(Connection connection, List<String> mailList) {
        String SQL = "INSERT INTO direcc_email(direccion) VALUES (?)";
        try{
           PreparedStatement preparedstatement;
           for (String mail: mailList){
                preparedstatement = connection.prepareStatement(SQL);
                preparedstatement.setString(1, mail);
                preparedstatement.executeUpdate();
                System.out.println("Email añadido : " + mail);
           }
        }
        catch (SQLException exception) {
            System.out.println("ERROR Kata5_insertEmails: (SQLException)" + exception.getMessage());
        }
        
    }
    
}
