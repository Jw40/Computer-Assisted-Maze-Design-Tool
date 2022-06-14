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
     * A SQL Statement to insert Data.
     */
    public static PreparedStatement insertData;

    /**
     * A SQL Statement to get mazedata by the author's name
     */
    private static PreparedStatement getMazeDataByName;

    /**
     * A SQL Statement to get mazedata by either the author's or maze's name
     */
    private static PreparedStatement getAuthorOrMazeName;



    /**
     * Constructor for DBStatement
     */
    Connection connection = DBConnection.getInstance();
    public DBStatements() {

        try {
            Statement statement = connection.createStatement();
//            insertData = connection.prepareStatement(DBQueries.INSERT_DATA);
            insertData = connection.prepareStatement(DBQueries.INSERT_DATA);
            getMazeDataByName = connection.prepareStatement(DBQueries.GET_MAZEDATA_BY_MAZENAME_AND_AUTHORNAME);

//            getAllMazeData = connection.prepareStatement(DBQueries.GET_ALL_MAZEDATA);
//            connection.nativeSQL(INSERT_DATA);
        } catch (SQLException sqlex) {
        System.err.println("Access to the database was denied. Ensure MySQL server is running.");
    }
    }

    public void InsertData (String mazeName, String authorName, String creationDate) {

        try {
            System.out.println(mazeName);
            System.out.println(authorName);
            System.out.println(creationDate);
            insertData.setString(1, mazeName);
            insertData.setString(2, authorName);
            insertData.setString(3, creationDate);
            insertData.executeQuery();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

//    public void GetAuthorOrMaze(String query) {
//        try {
//            getAuthorOrMazeName.setString(1, query);
//            getAuthorOrMazeName.executeQuery();
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//    }

//    public void GetAuthorOrMaze(String query) {
//        // get all current entries
//        ResultSet result = null;
//        try {
//            getAuthorOrMazeName.setString(1, query);
//            result = getAuthorOrMazeName.executeQuery();
//            result.next();
//
//            result.getString(query);
//
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//    }

    // From Lec06-dataconn
    private static void displayContents(Statement st) throws SQLException {
        // get all current entries
        ResultSet rs = st.executeQuery(GET_MAZEDATA_BY_MAZENAME_AND_AUTHORNAME);

        // use metadata to get the number of columns
        int columnCount = rs.getMetaData().getColumnCount();

        // output the column names
        for (int i = 0; i < columnCount; i++) {
            System.out.printf("%-20s", rs.getMetaData().getColumnName(i + 1));
        }
        System.out.printf("%n");

        // output each row
        while (rs.next()) {
            for (int i = 0; i < columnCount; i++) {
                System.out.printf("%-20s", rs.getString(i + 1));
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }
    public void GetAuthorOrMaze(String query) {
        // get all current entries
        ResultSet result = null;
        try {
            getAuthorOrMazeName.setString(1, query);
            result = getAuthorOrMazeName.executeQuery();

            while(result.next()) {
                result.updateRow();
            }
            Statement statement = connection.createStatement();
            displayContents(statement);



        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    /**
     * test
     */
    public static void run() {
        try {

            getMazeDataByName.setString(1, "ur maze"); // MazeName
            getMazeDataByName.setString(2, "dex"); // Author Name
            getMazeDataByName.executeUpdate();

//            getAllMazeData.executeUpdate();

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }


}

