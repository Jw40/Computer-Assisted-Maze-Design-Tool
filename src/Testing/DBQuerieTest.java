package Testing;

import Model.DBConnection;
import Model.DBQueries;
import Model.DBStatements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * new db class
 */
public class DBQuerieTest {


    @BeforeEach
    void DBConnection() {
        DBConnection.getInstance();
    }

    @Test
    void run()
    {
        DBStatements.run();

    }
    @AfterEach
    void DBEndConnection()
    {

    }

}
