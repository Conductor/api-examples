package jdbc;

import beans.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Strings;
import dao.DAO;
import streaming.APIPathBuilder;
import streaming.StreamBuilder;
import utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by anihalani on 6/9/15. DataDumper class to stream endpoint data and interact with DAO
 */

public class APIDataDumper {

    private static final String ENDPOINT_ACCOUNTS = "accounts";
    private static final String ENDPOINT_LOCATIONS = "locations";
    private static final String ENDPOINT_RANK_SOURCES = "rank-sources";
    private static final String ENDPOINT_DEVICES = "devices";
    private static final String ENDPOINT_WEB_PROPERTY = "web-properties";

    private APIPathBuilder pathBuilder;
    private DAO dao;
    private StreamBuilder streamBuilder;

    /**
     * Closes JDBC Connection
     */
    public void closeConnection() {
        dao.closeConnection();
    }

    /**
     * Commits the SQL Transaction
     */
    public void commit() {
        dao.commit();
    }

    /**
     *
     * @param dao
     *            - The dao instance to be associated with current Data Dumper Instance
     */
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    /**
     * StreamBuilder Setter
     *
     * @param streamBuilder
     *            - the streamBuilder instance to be set to
     */
    public void setStreamBuilder(StreamBuilder streamBuilder) {
        this.streamBuilder = streamBuilder;
    }

    /**
     * Constructor to create an instance of API Path Builder
     *
     * @param url
     *            - The Conductor API baseUrl
     */
    public APIDataDumper(String url) {
        pathBuilder = new APIPathBuilder(url);
        dao = new DAO();
        streamBuilder = new StreamBuilder();
    }

    /**
     * Sets the APIPathBuilder property for the class
     *
     * @param pathBuilder
     *            - The APIPathBuilder instance
     */
    public void setPathBuilder(APIPathBuilder pathBuilder) {
        this.pathBuilder = pathBuilder;
    }

    /**
     * Writes the location data returned from the API endpoint to the local database
     *
     */
    public void getLocationData() {
        String locationsUrl = pathBuilder.addKeyAndSignature(pathBuilder.buildWithEndpoint(ENDPOINT_LOCATIONS, null));
        writeObjects(locationsUrl, Location.class);
    }

    /**
     * Writes the rank_source data returned from the API endpoint to the local database
     *
     */
    public void getRankSourceData() {
        String rankSourcesUrl = pathBuilder.addKeyAndSignature(pathBuilder.buildWithEndpoint(ENDPOINT_RANK_SOURCES,
                null));
        writeObjects(rankSourcesUrl, RankSource.class);
    }

    /**
     * Writes the device data returned from the API endpoint to the local database
     */
    public void getDeviceData() {
        String devicesUrl = pathBuilder.addKeyAndSignature(pathBuilder.buildWithEndpoint(ENDPOINT_DEVICES, null));
        writeObjects(devicesUrl, Device.class);
    }

    /**
     * Writes the WebProperties data returned from the API endpoint to the local database
     */
    public void getWebPropertiesData() {

        Properties properties = Util.readProperties(Util.PROPS_FILE);
        String[] accounts = properties.getProperty("ACCOUNTS").split(",");

        try {
            for (String account : accounts) {
                String webPropertyUrl = pathBuilder.buildWithEndpoint(ENDPOINT_ACCOUNTS, account);
                webPropertyUrl = pathBuilder.addEndPointWithValue(webPropertyUrl, ENDPOINT_WEB_PROPERTY, null);
                webPropertyUrl = pathBuilder.addKeyAndSignature(webPropertyUrl);
                Thread.sleep(1000);
                writeObjects(webPropertyUrl, WebProperty.class);

            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception in APIDataDumper.getWebProperties()");
            e.printStackTrace();
        }

    }

    /**
     * Gets the Tracked Search data returned from the API endpoint
     */
    public void writeTrackedSearchData(String urlWithoutSig) {
        if (!Strings.isNullOrEmpty(urlWithoutSig)) {
            String trackedSearchUrl = pathBuilder.addKeyAndSignature(urlWithoutSig);
            writeObjects(trackedSearchUrl, TrackedSearch.class);
        }
    }

    /**
     * Generates URl by getting Account id, Web-Property id and Rank Source id from database and gets the Web Property
     * Rank Report data from API endpoints.
     */
    public void getWebPropertyRankReport(WebProperty webProperty) throws RuntimeException {
        String rankReportContext, volumeContext;
        for (RankSourceInfo rankSourceInfo : webProperty.getRankSourceInfo()) {
            rankSourceInfo.getReports().getTimePeriod();
            volumeContext = rankSourceInfo.getReports().getTimePeriod().getWebPropertySearchVolumeReport();
            rankReportContext = rankSourceInfo.getReports().getTimePeriod().getWebPropertyRankReport();
            writeSearchVolumeData(volumeContext);
            String webPropertyRankReportUrl = pathBuilder.addKeyAndSignature(rankReportContext);

            Function<Object, Object> timePeriodAddingFunction = o -> {
                ((ClientWebPropertyRankReport) o).setEndDate(rankSourceInfo.getReports().getTimePeriod().getEndDate());
                return o;
            };

            writeObjects(webPropertyRankReportUrl, ClientWebPropertyRankReport.class, timePeriodAddingFunction);
        }
    }

    /**
     * Gets data from the searchVolume Endpoint of the API
     * 
     * @param url
     *            - The url Path containing the accountId, webPropertyId and rankSourceId
     */
    private void writeSearchVolumeData(String url) {
        writeObjects(pathBuilder.addKeyAndSignature(url), ClientWebPropertySearchVolumeReport.class);
    }

    /**
     *
     * Streams data from an endpoint one object at a time and writes it to the local database
     *
     * @param url
     *            - the complete url with the endpoint parameter, the API key and the signature
     * @param cl
     *            - the class of the objects expected to be returned in the Json
     * @param beanDataTransformer
     *            - The Transformer function used to add endDate in the Rank Report objects
     */
    private void writeObjects(String url, Class cl, Function<Object, Object> beanDataTransformer) {
        InputStream inStream = null;

        try {

            inStream = streamBuilder.buildInStream(url);
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(inStream);
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY) {
                    continue;
                }
                // Read an object and write it to Database
                Object object = objectMapper.readValue(jParser, cl);

                if (beanDataTransformer != null) {
                    object = beanDataTransformer.apply(object);
                }

                // Write the comparison web properties and the tracked search data for each web property
                if (object instanceof WebProperty) {
                    ((WebProperty) object).setAccountId(getAccountIdFromUrl(url));
                    dao.writeToDatabase(object);
                    Thread.sleep(1000);
                    writeComparisonWebProperties((WebProperty) object);
                    Thread.sleep(1000);
                    writeTrackedSearchData(((WebProperty) object).getTrackedSearchList());
                    Thread.sleep(1000);
                    getWebPropertyRankReport(((WebProperty) object));
                } else if (object instanceof TrackedSearch) {
                    ((TrackedSearch) object).setWebPropertyId(getWebPropertyIdFromUrl(url));
                    dao.writeToDatabase(object);
                } else if (object instanceof ClientWebPropertySearchVolumeReport) {
                    for (VolumeItem volumeItem : ((ClientWebPropertySearchVolumeReport) object).getVolumeItems()) {
                        volumeItem.setTrackedSearchId(((ClientWebPropertySearchVolumeReport) object)
                                .getTrackedSearchId());
                        dao.writeToDatabase(volumeItem);
                    }
                    dao.writeToDatabase(object);
                } else if (object instanceof ClientWebPropertyRankReport) {
                    dao.writeToDatabase(object);
                } else {
                    dao.writeToDatabase(object);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Exception occurred in DatDumper.writeObjects for url %s", url), e);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing stream in writeObjects");
                e.printStackTrace();
            }
        }

    }

    /**
     * Streams data from an endpoint one object at a time and writes it to the local database
     *
     * @param url
     *            - the complete url with the endpoint parameter, the API key and the signature
     * @param cl
     *            - the class of the objects expected to be returned in the Json
     */
    private void writeObjects(String url, Class cl) {
        writeObjects(url, cl, null);
    }

    /**
     * Writes the comparison web properties for a given web property
     * 
     * @param webProperty
     *            - the WebProperty object whose comparison Web Properties are to be written to the database
     * @throws Exception
     *             - if connection is null and Exception is thrown from DAO.writeToDatabase
     */
    private void writeComparisonWebProperties(WebProperty webProperty) throws Exception {
        for (RankSourceInfo rankSourceInfo : webProperty.getRankSourceInfo()) {
            for (ComparisonWebProperty comparisonWebProperty : rankSourceInfo.getComparisonWebProperties()) {
                dao.writeToDatabase(comparisonWebProperty);
            }
        }
    }

    /**
     * Extracts and returns the account-id used in the web-property endpoint Url
     * 
     * @param url
     *            - The complete URL for Web-Property endpoint
     * @return - The accountId from the URL
     */
    private int getAccountIdFromUrl(String url) {
        String[] tokens = url.split("/");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("accounts")) {
                return Integer.parseInt(tokens[i + 1]);
            }
        }
        return 0;
    }

    /**
     * Extracts web property id from the tracked search url
     * 
     * @param url
     *            - The complete url for tracked-search endpoint
     * @return - The WebProperty id from the url
     */
    private int getWebPropertyIdFromUrl(String url) {
        String[] tokens = url.split("/");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("web-properties")) {
                return Integer.parseInt(tokens[i + 1]);
            }
        }
        return 0;
    }
}
