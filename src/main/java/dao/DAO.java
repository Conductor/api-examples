package dao;

import beans.*;
import com.google.common.base.Strings;
import jdbc.JDBCConnection;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by anihalani on 6/9/15. DAO class to handle database interactions
 */
public class DAO {

    public static Connection conn;

    /**
     * Constructor
     */
    public DAO() {
        try {
            conn = JDBCConnection.getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred in while setting Auto Commit to false", e);
        }
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

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Exception occurred while committing the transaction!", e);
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
            case "beans.ClientWebPropertySearchVolumeReport":
                preparedStmt = getWebPropertySearchVolumeReportStmt(object);
                break;
            case "beans.VolumeItem":
                preparedStmt = getVolumePreparedStmt(object);
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
        String query = "insert ignore into device (device_id, description) values (?, ?)";

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
        String query = "insert ignore into locale (locale_id, description) values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, location.getLocationId());
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
        String query = "insert ignore into rank_source (rank_source_id, base_url, description, name) values (?, ?, ?, ?)";

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
        String query = "insert ignore into web_property (web_property_id, name, account_id) values (?, ?, ?)";

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
        String query = "insert ignore into web_property (web_property_id, name, label) values (?, ?, ?)";

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
        String query = "insert ignore into tracked_search (tracked_search_id, status_id, preferred_url, query_phrase, location_id, rank_source_id, device_id, web_property_id ) values (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, trackedSearch.getTrackedSearchId());
        preparedStmt.setInt(2, (trackedSearch.getActive() ? 1 : 2));
        preparedStmt.setString(3, trackedSearch.getPreferredUrl());
        preparedStmt.setString(4, trackedSearch.getQueryPhrase());
        preparedStmt.setInt(5, trackedSearch.getLocationId());
        preparedStmt.setInt(6, trackedSearch.getRankSourceId());
        preparedStmt.setInt(7, trackedSearch.getDeviceId());
        preparedStmt.setInt(8, trackedSearch.getWebPropertyId());
        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the web_property_rank_report table
     * 
     * @param webPropRankReportObject
     *            - a beans.ClientWebPropertyRankReport instance wrapped in generic Java object
     * @return - The prepared statement for query to insert row in web property rank report
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getWebPropertyRankReportPreparedStmt(Object webPropRankReportObject) throws SQLException,
            ParseException {
        ClientWebPropertyRankReport webPropertyRankReport = (ClientWebPropertyRankReport) webPropRankReportObject;

        // Execute a query
        // the mysql insert statement
        String query = "insert ignore into client_web_property_rank_report (universal_rank, true_rank, classic_rank, web_property_id, tracked_search_id, item_type, target, target_domain_name, target_url, time_period_end) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong(1, webPropertyRankReport.getRanks().getUniversalRank());
        preparedStmt.setLong(2, webPropertyRankReport.getRanks().getTrueRank());
        preparedStmt.setLong(3, webPropertyRankReport.getRanks().getClassicRank());
        preparedStmt.setLong(4, webPropertyRankReport.getWebPropertyId());
        preparedStmt.setLong(5, webPropertyRankReport.getTrackedSearchId());
        preparedStmt.setString(6, webPropertyRankReport.getItemType());
        preparedStmt.setString(7, webPropertyRankReport.getTarget());
        preparedStmt.setString(8, webPropertyRankReport.getTargetDomainName());
        preparedStmt.setString(9, webPropertyRankReport.getTargetUrl());
        if (!Strings.isNullOrEmpty(webPropertyRankReport.getEndDate())) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            java.util.Date date = df.parse(webPropertyRankReport.getEndDate());
            preparedStmt.setDate(10, new Date(date.getTime()));
        } else {
            preparedStmt.setDate(10, null);
        }
        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the web_property_rank_report table
     *
     * @param webPropSearchVolObject
     *            - a beans.ClientWebPropertySearchVolumeReport instance wrapped in generic Java object
     * @return - The prepared statement for query to insert row in web property search volume report
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getWebPropertySearchVolumeReportStmt(Object webPropSearchVolObject) throws SQLException {
        ClientWebPropertySearchVolumeReport webPropertySearchVolumeReport = (ClientWebPropertySearchVolumeReport) webPropSearchVolObject;

        // Execute a query
        // the mysql insert statement
        String query = "insert ignore into client_web_property_search_volume_report (average_volume, tracked_search_id) values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong(1, webPropertySearchVolumeReport.getAverageVolume());
        preparedStmt.setLong(2, webPropertySearchVolumeReport.getTrackedSearchId());
        return preparedStmt;
    }

    /**
     * Returns a prepared statement for inserting a row in the tracked_search_volume table
     *
     * @param volumeObject
     *            - a beans.VolumeItem instance wrapped in generic Java object
     * @return - The prepared statement for query to insert row in tracked_search_volume
     * @throws SQLException
     *             - if there is a problem with setting the parameters for the preparedStatement
     */
    private PreparedStatement getVolumePreparedStmt(Object volumeObject) throws SQLException {
        VolumeItem volumeItem = (VolumeItem) volumeObject;

        // Execute a query
        // the mysql insert statement
        String query = "insert ignore into tracked_search_volume (tracked_search_id, month, year, volume ) values (?, ?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setLong(1, volumeItem.getTrackedSearchId());
        preparedStmt.setInt(2, volumeItem.getMonth());
        preparedStmt.setInt(3, volumeItem.getYear());
        preparedStmt.setLong(4, volumeItem.getVolume());
        return preparedStmt;
    }

    /**
     * Checks if a web property already exists in the web_property table
     *
     * @param webPropertyId
     *            - the web_property_id to be checked
     * @return true - if the web property exists in the web_property table, else return false
     */
    private boolean webPropertyExists(int webPropertyId) throws SQLException {

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            // Execute a query
            // the mysql select statement
            String query = "select * from web_property where web_property_id=" + webPropertyId;
            rs = stmt.executeQuery(query);
            rs.beforeFirst();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DAO.webPropertyExits");
            e.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
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
    private boolean trackedSearchExists(int trackSearchId) throws SQLException {

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            // Execute a query
            // the mysql select statement
            String query = "select * from tracked_search where tracked_search_id=" + trackSearchId;
            rs = stmt.executeQuery(query);
            rs.beforeFirst();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in DAO.trackedSearchExists");
            e.printStackTrace();
        } finally {
            rs.close();
            stmt.close();
        }
        return false;
    }
}
