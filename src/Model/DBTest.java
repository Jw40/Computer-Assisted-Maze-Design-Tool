package Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test database
 */
public class DBTest {
    /**
     * @param args test
     */
    public static void main(String[] args) {
        // Start connection to the MariaDB
        Connection connection = DBConnection.getInstance();
//        DBStatements.insertData();


        try {
            Statement statement = connection.createStatement();
//            var insertData = connection.prepareStatement(DBQueries.INSERT_DATA);
//            var getMazeDataByName = connection.prepareStatement(DBQueries.GET_MAZEDATA_BY_MAZENAME_AND_AUTHORNAME);
//            // Needs updating and fixing
//            var getAllMazeData = connection.prepareStatement(DBQueries.GET_ALL_MAZEDATA);

            statement.GetAuthorOrMaze("dex");
        } catch (SQLException sqlex) {
            System.err.println("Access to the database was denied. Ensure MySQL server is running.");
        }

        DBStatements statements = new DBStatements();
        statements.run();
    }
}
