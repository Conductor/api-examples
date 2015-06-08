package dao;

import beans.RankSource;
import sql.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by anihalani on 6/8/15.
 */
public class RankedSourceDAO {
    /**
     * Writes the values in the RankSource object to the 'rank_source' table in the local database
     * @throws Exception - if connection is null
     */
    public static void writeToDatabase(RankSource rankSource) throws Exception {
        Connection conn = JDBCConnection.getConnection();
        if (conn==null)
            throw new Exception("Connection not successful!");
        // Execute a query
        // the mysql insert statement
        String query = "insert into rank_source (rank_source_id, base_url, description, name) values (?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, rankSource.getRankSourceId());
            preparedStmt.setString(2, rankSource.getBaseDomain());
            preparedStmt.setString(3, rankSource.getDescription());
            preparedStmt.setString(4, rankSource.getName());
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
