package Model;

import java.sql.*;
//import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// Adapted from find the GGITHUB LINK

/**
 * This class contains the database access and it's required statements
 */
public class DBStatements extends DBDataSource {
    /**
     * A SQL Statement to find a user.
     */
    private static PreparedStatement insertData;

    /**
     * A SQL Statement to get maze size
     */
    private PreparedStatement mazeSize;

    /**
     * Constructor for DBStatement
     */
    public DBStatements() {
        Connection connection = DBConnection.getInstance();
        try {
            Statement statement = connection.createStatement();
//            insertData = connection.prepareStatement(DBQueries.INSERT_DATA);
            insertData = connection.prepareStatement(DBQueries.INSERT_DATA);
//            connection.nativeSQL(INSERT_DATA);
        } catch (SQLException sqlex) {
        System.err.println("Access to the database was denied. Ensure MySQL server is running.");
    }
    }

    /**
     * test
     */
    public void run() {
        try {
            // Inserting Data to the database
            insertData.setString(1, "foo_maze_data"); // change with the maze
            insertData.setString(2, "Foo authorname"); // change with author name
            insertData.setString(3, "fooMazeName"); // change with maze data string
            insertData.setString(4, "2022-02-22");
            insertData.executeUpdate();



        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }


}

