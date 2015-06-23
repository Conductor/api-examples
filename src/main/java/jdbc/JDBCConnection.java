package jdbc;

import utils.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by anihalani on 5/29/15.
 */
public class JDBCConnection {
    public static final Properties properties = Util.readProperties(Util.PROPS_FILE);
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * Method to create a JDBC connection and return it.
     * 
     * @return JDBC Connection
     */
    public static Connection getConnection() throws RuntimeException{
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection and return it
            return DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (Exception e) {
            // Handle errors for JDBC
            // Handle errors for Class.forName
            System.out.println("Exception in JDBCConnection.getConnection");
            throw new RuntimeException("Unable to create a connection using Class.forName(JDBC_DRIVER) in JDBC.getConnection ", e);
        }
    }
}
