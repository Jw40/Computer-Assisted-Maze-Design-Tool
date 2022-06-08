package Model;

import java.sql.*;
//import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;

// adapted from db.sql from https://github.com/itslewiswatson/cab302-major-project

/**
 *  implement pre defined sql commands and other database collection related stuff
 *
 */
public class Database {
    /**
     * Using MariaDB SQL Commands
     * Creating the users table
     */
    public static final String CREATE_USERS_TABLE =
            "CREATE TABLE IF NOT EXISTS user ("
                    + "idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,"
                    + "username VARCHAR(30) NOT NULL,"
                    + "password VARCHAR(30) NOT NULL.";

    /**
     * SQL Commands to create Maze info table
     */
    public static final String CREATE_MAZE_INFO_TABLE =
            "CREATE TABLE IF NOT EXISTS user_prefs ("
                    + "idx INT PRIMARY KEY NOT NULL UNIQUE,"
                    + "maze_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE, "
                    + "mazeName VARCHAR(5)," // Maze Size
                    + "dateCreated DATE,"
                    + "FOREIGN KEY (idx) REFERENCES user(idx)";


//    /**
//     * User's Maze preferences
//     */
//    private static final String CREATE_MAZE_PREFS_TABLE =
//            "CREATE TABLE IF NOT EXISTS maze_Prefs ("
//                    + "idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE"
//                    + "asdfasdf";
//}









    /**
     * Constructor for Creating the SQL Tables
     */

    public Database() {
//        try {
//            // Creating tables
//            Statement statement = connection.createStatement();
//            statement.execute(CREATE_USERS_TABLE);
//        } catch (SQLException exception) {
//            System.err.println("Access to the database was denied. Ensure MySQL server is running.");
//        }

        try {
            // Creating tables
            Connection connection = DBConnection.getInstance();
            connection.execute(CREATE_USERS_TABLE);
            connection.execute(CREATE_MAZE_INFO_TABLE);
        } catch (SQLException exception) {
            System.err.println("Access to the database was denied. Ensure MySQL server is running.");
        }
    }
}
