package Model;

/**
 * SQL Commands to be inserted into
 * ? is variable to be replaced by functions and their variables
 * e.g. Values will become (York, P455w0rd)
 */
public class DBQueries {



    /**
     * SQL Query to Insert a user into the database
     * Username and password along with an ID number that is given automatically
     */
    public static final String INSERT_DATA =
            "INSERT INTO user_data (authorName, mazeName, creationDate) VALUES ( ?, ?, ?);"; // IDX is auto incrementing

    /**
     * SQL Query to get mazeData by using the mazeName and authorName
     * Takes mazeName and authorName and returns a mazeData name.
     */
    public static final String GET_MAZEDATA_BY_MAZENAME_AND_AUTHORNAME =
            "SELECT * FROM user_data WHERE mazeName = '?' and authorName = '?'";

    /**
     * SQL Query to get all Maze Data Information
     */
    public static final String GET_ALL_MAZEDATA =
            "SELECT * FROM user_data";

    /**
     * SQL Query to get all Maze Data Information
     */
    public static final String UPDATE_MAZE_NAME =
            "UPDATE ";

    // TODO
    // Search by author name
    // Search by Maze name
    // Update Maze
}
