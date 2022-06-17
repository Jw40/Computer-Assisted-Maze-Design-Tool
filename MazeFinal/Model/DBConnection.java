package Model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * This class represents the connection to the Maria Database
 * Adapted from
 */
public class DBConnection {


    private static Connection instance = null;

    /**
     * Constructor intializes the connection.
     */
    private DBConnection() {

        Properties prop = new Properties();
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream("./db.props");
            prop.load(inStream);
            inStream.close();

            // specify the data source, username and password
            String url = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            String schema = prop.getProperty("jdbc.schema");

            // get a connection
            instance = DriverManager.getConnection(url + "/" + schema, username,
                    password);
        } catch (SQLException sqle) {
            System.err.println(sqle);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } catch (IOException IOex) {
            IOex.printStackTrace();
        }
        System.out.println("Connected successfully");
    }

    /**
     * Provides global access to the singleton instance of the UrlSet.
     *
     * @return a handle to the singleton instance of the UrlSet.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }
}