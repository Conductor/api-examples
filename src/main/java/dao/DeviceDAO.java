package dao;

import beans.Device;
import sql.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by anihalani on 6/8/15.
 */
public class DeviceDAO {
    /**
     * Writes the values in the Device object to the 'device' table in the local database
     * @throws Exception - if connection is null
     */
    public static void writeToDatabase(Device device) throws Exception {
        Connection conn = JDBCConnection.getConnection();
        if (conn==null)
            throw new Exception("Connection not successful!");
        // Execute a query
        // the mysql insert statement
        String query = "insert into device (device_id, description) values (?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, device.getDeviceId());
            preparedStmt.setString(2, device.getDescription());
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.err.println("Error in closing connection!");
                e.printStackTrace();
            }
        }
    }
}
