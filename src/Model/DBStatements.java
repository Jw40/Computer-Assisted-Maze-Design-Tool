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
    private PreparedStatement insertUser;

    /**
     * A SQL Statement to get maze size
     */
    private PreparedStatement mazeSize;

    public DBStatements() {
        Connection connection = DBConnection.getInstance();
        connection = connection.execute(CREATE_USERS_TABLE);
        try {

            insertUser = connection.prepareStatement(DBQueries.INSERT_USER);



        } catch (SQLException sqlex) {
        System.err.println("Access to the database was denied. Ensure MySQL server is running.");
    }
    }
}