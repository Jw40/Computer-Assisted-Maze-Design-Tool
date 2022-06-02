package Model;

import java.sql.PreparedStatement;


// Adapted from https://github.com/itslewiswatson/cab302-major-project

/**
 * This class contains the database access and it's required statements
 */
public class DBStatements extends DBDataSource {
    /**
     * A SQL Statement to find a user.
     */
    private PreparedStatement insertUser;
}