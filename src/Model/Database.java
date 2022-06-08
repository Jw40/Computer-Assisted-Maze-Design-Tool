package Model;

import java.sql.Statement;

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
     * SQL Commands to be inserted into
     * ? is variable to be replaced by functions and their variables
     * e.g. Values will become (York, P455w0rd)
     */

    private static final String INSERT_USER =
            "INSERT INTO user (username, password) VALUES (?, ?);"; // IDX is auto incrementing
    private static final String SEARCH_USER =
            "SELECT * FROM users where username = ?";

    /**
     * SQL Commands to create Maze info table
     */
    public static final String CREATE_MAZE_INFO =
            "CREATE TABLE IF NOT EXISTS user_prefs ("
                    + "idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,"
                    + "mazeName VARCHAR(5)," // Maze Size
                    + "dateCreated VARCHAR(5)";

    private static final String INSERT_MAZE_INFO =
            "INSERT INTO user_prefs (mazeName, dateCreated) VALUES (?, ?);";

    private static final String GET_MAZE_INFO =
            "SELECT * FROM user_prefs WHERE idx = ?;"; // Searching by IDX might not be the best way, might change later

    private static final String GET_ALL_BY_ID =
            "SELECT * FROM user_prefs";

    private static final String EDIT_MAZENAME =
            "UPDATE user_prefs set mazeName = ? WHERE idx = ?";

    /**
     * User's Maze preferences
     */
    private static final String CREATE_MAZE_PREFS =
            "CREATE TABLE IF NOT EXISTS maze_Prefs ("

                    + "idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE"
                    + "asdfasdf"


                + "idx INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE";








    /**
     * Constructor for Creating the SQL Tables
     */

    public Database() {
        try {
            // Creating tables
            //Statement statement = connection.createStatement();
            //statement.execute
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

