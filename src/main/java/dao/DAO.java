package dao;

import beans.*;
import jdbc.JDBCConnection;

import java.sql.*;

/**
 * Created by anihalani on 6/9/15.
 */
public class DAO {

    public static Connection conn;

    /**
     * Constructor
     */
    public DAO() {
        conn = JDBCConnection.getConnection();
    }

    /**
     * Closes the JDBC connection
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error in DAO.closeConnection");
            e.printStackTrace();
        }
    }

    /**
     * Writes the values in the beans object to the respective table in the local database
     *
     * @throws Exception
     *             - if connection is null
     */
    public void writeToDatabase(Object object) throws Exception {
        if (conn == null) {
            throw new Exception("Connection not successful!");
        }

        try {
            PreparedStatement preparedStmt;
            switch (object.getClass().getName()) {
            case "beans.Device":
                preparedStmt = getDevicePreparedStmt(object);
                break;
            case "beans.Location":
                preparedStmt = getLocationsPreparedStmt(object);
                break;
            case "beans.RankSource":
                preparedStmt = getRankSourcePreparedStmt(object);
                break;
            case "beans.WebProperty":
                preparedStmt = getWebPropertyPreparedStmt(object);
                break;
            case "beans.ComparisonWebProperty":
                preparedStmt = getComparisonWebPropertyPreparedStmt(object);
                break;
            case "beans.TrackedSearch":
                preparedStmt = getTrackedSearchPreparedStmt(object);
                break;
            case "beans.ClientWebPropertyRankReport":
                preparedStmt = getWebPropertyRankReportPreparedStmt(object);
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
            throw e;
        }
    }

    /**
     * Returns a prepared statement for inserting a row in the devices table
     * 
     * @param deviceObject
     *            - a beans.Device instance wrapped in generic Java object
     * @return -the prepared statement for query to insert row in device table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getDevicePreparedStmt(Object deviceObject) throws SQLException {
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
     * @param locationObject
     *            - a beans.Location instance wrapped in generic Java object
     * @return - the prepared statement for query to insert row in locale table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getLocationsPreparedStmt(Object locationObject) throws SQLException {
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
     * @param rankSourceObject
     *            - a beans.RankSource instance wrapped in generic Java object
     * @return - he prepared statement for query to insert row in web rank_source table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getRankSourcePreparedStmt(Object rankSourceObject) throws SQLException {
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
     * @param webPropertyObject
     *            - a beans.WebProperty instance wrapped in generic Java object
     * @return - the prepared statement for query to insert row in web_property table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getWebPropertyPreparedStmt(Object webPropertyObject) throws SQLException {
        WebProperty webProperty = (WebProperty) webPropertyObject;

        if (webPropertyExists(webProperty.getWebPropertyId())) {
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
     * @param comparisonWebPropertyObject
     *            - a beans.ComparisonWebProperty instance wrapped in generic Java object
     * @return - the prepared statement for query to insert row in web_property table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getComparisonWebPropertyPreparedStmt(Object comparisonWebPropertyObject)
            throws SQLException {
        ComparisonWebProperty comparisonWebProperty = (ComparisonWebProperty) comparisonWebPropertyObject;

        if (webPropertyExists(comparisonWebProperty.getWebPropertyId())) {
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
     * @param trackedSearchObject
     *            - a beans.TrackedSearch instance wrapped in generic Java object
     * @return - the prepared statement for query to insert row in tracked_search table
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getTrackedSearchPreparedStmt(Object trackedSearchObject) throws SQLException {
        TrackedSearch trackedSearch = (TrackedSearch) trackedSearchObject;

        if (trackedSearchExists(trackedSearch.getTrackedSearchId())) {
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

    /**
     * Returns a prepared statement for inserting a row in the web_property_rank_report table
     * 
     * @param webPropRankReportObject
     *            - a beans.WebPropertyRankReport instance wrapped in generic Java object
     * @return - The prepared statement for query to insert row in web property rank report
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getWebPropertyRankReportPreparedStmt(Object webPropRankReportObject) throws SQLException {
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
     * @param webPropertyId
     *            - the web_property_id to be checked
     * @return true - if the web property exists in the web_property table, else return false
     */
    private boolean webPropertyExists(int webPropertyId) {

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
     * @param trackSearchId
     *            - the web_property_id to be checked
     * @return true - if the web property exists in the web_property table, else return false
     */
    private boolean trackedSearchExists(int trackSearchId) {

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

    /**
     * Returns the data with account id, web_property_id and rank_source_id needed to get rank report data
     * 
     * @return the result set generated by the database query
     */

    public ResultSet getRankSourceIdsFromTrackedSearch() {
        Statement stmt;
        ResultSet rs = null;
        try {
            if (conn != null) {
                conn = JDBCConnection.getConnection();
                stmt = conn.createStatement();
                // Execute a query
                // the mysql select statement
                String query = "select DISTINCT web_property.account_id, " + "tracked_search.web_property_id, "
                        + "tracked_search.rank_source_id " + "from web_property, tracked_search "
                        + "where web_property.web_property_id = tracked_search.web_property_id;";
                rs = stmt.executeQuery(query);

            }
            return rs;
        } catch (SQLException e) {
            System.out.println("Error in DAO.trackedSearchExists");
            e.printStackTrace();
        }
        return null;
    }

}
