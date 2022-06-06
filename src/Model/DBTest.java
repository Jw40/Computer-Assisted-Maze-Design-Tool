package Model;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        // Start connection to the MariaDB
        Connection connection = DBConnection.getInstance();


    }
}
