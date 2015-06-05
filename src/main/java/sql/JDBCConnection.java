package sql;

import utils.StringUtility;
import utils.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by anihalani on 5/29/15.
 */
public class JDBCConnection {
    public static final Properties properties = Util.readProperties(Util.PROPS_FILE);
    /**
     * Method to create a JDBC connection and return it.
     * 
     * @return JDBC Connection
     */
    public static Connection getConnection() {

        try {
            // STEP 2: Register JDBC driver
            Class.forName(StringUtility.JDBC_DRIVER);
            // STEP 3: Open a connection and return it
            return DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
        return null;
    }
}// end JDBCExample

