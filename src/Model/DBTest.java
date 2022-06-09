package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
    public static void main(String[] args) {
        // Start connection to the MariaDB
        Connection connection = DBConnection.getInstance();
//        DBStatements.insertData();


        try {
            Statement statement = connection.createStatement();
            var insertData = connection.prepareStatement(DBQueries.INSERT_DATA);


        } catch (SQLException sqlex) {
            System.err.println("Access to the database was denied. Ensure MySQL server is running.");
        }
    }
}
