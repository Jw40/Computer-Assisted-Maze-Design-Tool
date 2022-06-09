package Model;

import java.sql.*;
//import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;



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
}

