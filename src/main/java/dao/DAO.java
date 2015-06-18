package dao;

import beans.*;
import jdbc.JDBCConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anihalani on 6/9/15.
 */
public class DAO {

    public static Connection conn;

    /**
     * Writes the values in the beans object to the respective table in the local database
     *
     * @throws Exception - if connection is null
     */
    public static void writeToDatabase(Object object) throws Exception {
        conn = JDBCConnection.getConnection();
        if (conn == null) {
            throw new Exception("Connection not successful!");
        }

        try {
            PreparedStatement preparedStmt;
            switch (object.getClass().getName()) {
                case "beans.Device":
                    preparedStmt = getDevicePreparedStmt(conn, object);
                    break;
                case "beans.Location":
                    preparedStmt = getLocationsPreparedStmt(conn, object);
                    break;
                case "beans.RankSource":
                    preparedStmt = getRankSourcePreparedStmt(conn, object);
                    break;
                case "beans.WebProperty":
                    preparedStmt = getWebPropertyPreparedStmt(conn, object);
                    break;
                case "beans.ComparisonWebProperty":
                    preparedStmt = getComparisonWebPropertyPreparedStmt(conn, object);
                    break;
                case "beans.TrackedSearch":
                    preparedStmt = getTrackedSearchPreparedStmt(conn, object);
                    break;
                case "beans.ClientWebPropertyRankReport":
                    preparedStmt = getWebPropertyRankReportPreparedStmt(conn, object);
                    break;
                default:
                    preparedStmt = null;
                    break;
            }

            if (preparedStmt != null) {
                preparedStmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Error in DAO.writeToDatabase");
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error in closing connection!");
                e.printStackTrace();
            }
        }
    }


    /**
     * Returns a prepared statement for inserting a row in the devices table
     *
     * @param conn         - the JDBC connection
     * @param deviceObject - a beans.Device instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getDevicePreparedStmt(Connection conn, Object deviceObject) throws SQLException {
        Device device = (Device) deviceObject;
        // Execute a query
        // the mysql insert statement
        String query = "insert into device (device_id, description) values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, device.getDeviceId());
        preparedStmt.setString(2, device.getDescription());

        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the locale table
     *
     * @param conn           - the JDBC connection
     * @param locationObject - a beans.Location instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getLocationsPreparedStmt(Connection conn, Object locationObject)
            throws SQLException {
        Location location = (Location) locationObject;
        // Execute a query
        // the mysql insert statement
        String query = "insert into locale (locale_id, description) values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, location.getLocationId());
        preparedStmt.setString(2, location.getDescription());

        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the rankSource table
     *
     * @param conn             - the JDBC connection
     * @param rankSourceObject - a beans.RankSource instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getRankSourcePreparedStmt(Connection conn, Object rankSourceObject)
            throws SQLException {
        RankSource rankSource = (RankSource) rankSourceObject;
        // Execute a query
        // the mysql insert statement
        String query = "insert into rank_source (rank_source_id, base_url, description, name) values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, rankSource.getRankSourceId());
        preparedStmt.setString(2, rankSource.getBaseDomain());
        preparedStmt.setString(3, rankSource.getDescription());
        preparedStmt.setString(4, rankSource.getName());

        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the web_property table
     *
     * @param conn              - the JDBC connection
     * @param webPropertyObject - a beans.WebProperty instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getWebPropertyPreparedStmt(Connection conn, Object webPropertyObject)
            throws SQLException {
        WebProperty webProperty = (WebProperty) webPropertyObject;

        if (webPropertyExists(webProperty.getWebPropertyId(), conn)) {
            return null;
        }
        // Execute a query
        // the mysql insert statement
        String query = "insert into web_property (web_property_id, name, account_id) values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, webProperty.getWebPropertyId());
        preparedStmt.setString(2, webProperty.getName());
        preparedStmt.setInt(3, webProperty.getAccountId());

        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the web_property table
     *
     * @param conn                        - the JDBC connection
     * @param comparisonWebPropertyObject - a beans.ComparisonWebProperty instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getComparisonWebPropertyPreparedStmt(Connection conn,
                                                                          Object comparisonWebPropertyObject) throws SQLException {
        ComparisonWebProperty comparisonWebProperty = (ComparisonWebProperty) comparisonWebPropertyObject;

        if (webPropertyExists(comparisonWebProperty.getWebPropertyId(), conn)) {
            return null;
        }

        // Execute a query
        // the mysql insert statement
        String query = "insert into web_property (web_property_id, name, label) values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, comparisonWebProperty.getWebPropertyId());
        preparedStmt.setString(2, comparisonWebProperty.getName());
        preparedStmt.setString(3, comparisonWebProperty.getLabel());

        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the tracked_search table
     *
     * @param conn                - the JDBC connection
     * @param trackedSearchObject - a beans.TrackedSearch instance wrapped in generic Java object
     * @return
     * @throws SQLException - if there is a problem with setting the parameters for the preparedStatement
     */
    private static PreparedStatement getTrackedSearchPreparedStmt(Connection conn, Object trackedSearchObject)
            throws SQLException {
        TrackedSearch trackedSearch = (TrackedSearch) trackedSearchObject;

        if (trackedSearchExists(trackedSearch.getTrackedSearchId(), conn)) {
            return null;
        }
        // Execute a query
        // the mysql insert statement
        String query = "insert into tracked_search (tracked_search_id, preferred_url, query_phrase, location_id, rank_source_id, device_id, web_property_id ) values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, trackedSearch.getTrackedSearchId());
        preparedStmt.setString(2, trackedSearch.getPreferredUrl());
        preparedStmt.setString(3, trackedSearch.getQueryPhrase());
        preparedStmt.setInt(4, trackedSearch.getLocationId());
        preparedStmt.setInt(5, trackedSearch.getRankSourceId());
        preparedStmt.setInt(6, trackedSearch.getDeviceId());
        preparedStmt.setInt(7, trackedSearch.getWebPropertyId());
        return preparedStmt;
    }

    private static PreparedStatement getWebPropertyRankReportPreparedStmt(Connection conn, Object webPropRankReportObject) throws SQLException {
        ClientWebPropertyRankReport webPropertyRankReport = (ClientWebPropertyRankReport) webPropRankReportObject;

        // Execute a query
        // the mysql insert statement
        String query = "insert into client_web_property_rank_report (universal_rank, true_rank, classic_rank, web_property_id, tracked_search_id, item_type, target, target_domain_name, target_url) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong(1, webPropertyRankReport.getRanks().getUNIVERSALRANK());
        preparedStmt.setLong(2, webPropertyRankReport.getRanks().getTRUERANK());
        preparedStmt.setLong(3, webPropertyRankReport.getRanks().getCLASSICRANK());
        preparedStmt.setLong(4, webPropertyRankReport.getWebPropertyId());
        preparedStmt.setLong(5, webPropertyRankReport.getTrackedSearchId());
        preparedStmt.setString(6, webPropertyRankReport.getItemType());
        preparedStmt.setString(7, webPropertyRankReport.getTarget());
        preparedStmt.setString(8, webPropertyRankReport.getTargetDomainName());
        preparedStmt.setString(9, webPropertyRankReport.getTargetUrl());
        return preparedStmt;
    }

    /**
     * Checks if a web property already exists in the web_property table
     *
     * @param webPropertyId - the web_property_id to be checked
     * @param conn          - the JDBC connection
     * @return true - if the web property exists in the web_property table, else return false
     */
    private static boolean webPropertyExists(int webPropertyId, Connection conn) {

        Statement stmt;
        try {
            stmt = conn.createStatement();
            // Execute a query
            // the mysql select statement
            String query = "select * from web_property where web_property_id=" + webPropertyId;
            ResultSet rs = stmt.executeQuery(query);
            rs.beforeFirst();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DAO.webPropertyExits");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if a web property already exists in the web_property table
     *
     * @param trackSearchId - the web_property_id to be checked
     * @param conn          - the JDBC connection
     * @return true - if the web property exists in the web_property table, else return false
     */
    private static boolean trackedSearchExists(int trackSearchId, Connection conn) {

        Statement stmt;
        try {
            stmt = conn.createStatement();
            // Execute a query
            // the mysql select statement
            String query = "select * from tracked_search where tracked_search_id=" + trackSearchId;
            ResultSet rs = stmt.executeQuery(query);
            rs.beforeFirst();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DAO.trackedSearchExists");
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet getRankSourceIdsFromTrackedSearch() {
        Statement stmt;
        List<Integer> rankSourceIds = new ArrayList<Integer>();
        try {
            conn = JDBCConnection.getConnection();
            stmt = conn.createStatement();
            // Execute a query
            // the mysql select statement
            String query = "select DISTINCT web_property.account_id, " +
                    "tracked_search.web_property_id, " +
                    "tracked_search.rank_source_id " +
                    "from web_property, tracked_search " +
                    "where web_property.web_property_id = tracked_search.web_property_id;";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println("Error in DAO.trackedSearchExists");
            e.printStackTrace();
        }
        return null;
    }

}
