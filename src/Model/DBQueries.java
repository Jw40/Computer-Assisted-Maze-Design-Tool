package Model;

public class DBQueries {

    /**
     * SQL Commands to be inserted into
     * ? is variable to be replaced by functions and their variables
     * e.g. Values will become (York, P455w0rd)
     */

    /*
     * SQL Query to Insert a user into the database
     * Username and password along with an ID number that is given automatically
     */
    public static final String INSERT_USER =
            "INSERT INTO user (username, password) VALUES (?, ?);"; // IDX is auto incrementing

    /*
     * SQL Query to Search a user in the database by their username
     */
    public static final String SEARCH_USER =
            "SELECT * FROM users where username = ?";
    /*
     * SQL Query to insert maze info such as the name of the maze and the date created
     */
    public static final String INSERT_MAZE_INFO =
            "INSERT INTO user_prefs (mazeName, dateCreated) VALUES (?, ?);";

    /*
     * SQL Query to search maze info such as the name of the maze and the date created
     */
    public static final String GET_MAZE_INFO =
            "SELECT * FROM user_prefs WHERE idx = ?;"; // Searching by IDX might not be the best way, might change later
    /*
     * SQL Query to select all maze info such as the name of the maze and the date created
     */
    public static final String GET_ALL_MAZE_INFO =
            "SELECT * FROM user_prefs";
    /*
     * SQL Query to edit maze name such as the name of the maze and the date created
     */
    public static final String EDIT_MAZENAME =
            "UPDATE user_prefs set mazeName = ? WHERE idx = ?";

}
