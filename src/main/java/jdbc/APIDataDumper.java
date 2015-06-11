package jdbc;

import beans.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DAO;
import streaming.APIPathBuilder;
import streaming.StreamBuilder;
import utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by anihalani on 6/9/15.
 */
public class APIDataDumper {

    private String baseUrl;
    private static final String ENDPOINT_LOCATIONS = "locations";
    private static final String ENDPOINT_RANK_SOURCES = "rank-sources";
    private static final String ENDPOINT_DEVICES = "devices";
    private static final String ENDPOINT_WEB_PROPERTY = "accounts/<<accountNumber>>/web-properties";

    private APIPathBuilder pathBuilder;

    /**
     * Constructor
     * 
     * @param url
     *            - The Conductor API baseUrl
     */
    public APIDataDumper(String url) {
        this.baseUrl = url;
        pathBuilder = new APIPathBuilder(url);
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
        String locationsUrl = pathBuilder.build(ENDPOINT_LOCATIONS);
        writeObjects(locationsUrl, Location.class);
    }

    /**
     * Writes the rank_source data returned from the API endpoint to the local database
     *
     */
    public void getRankSourceData() {
        String rankSourcesUrl = pathBuilder.build(ENDPOINT_RANK_SOURCES);
        writeObjects(rankSourcesUrl, RankSource.class);
    }

    /**
     * Writes the device data returned from the API endpoint to the local database
     */
    public void getDeviceData() {
        String devicesUrl = pathBuilder.build(ENDPOINT_DEVICES);
        writeObjects(devicesUrl, Device.class);
    }

    /**
     * Writes the WebProperties data returned from the API endpoint to the local database
     */
    public void getWebPropertiesData() {

        Properties properties = Util.readProperties(Util.PROPS_FILE);
        String[] accounts = properties.getProperty("ACCOUNTS").split(",");

        for (String account : accounts) {
            String webPropertyUrl = pathBuilder.build(ENDPOINT_WEB_PROPERTY.replace("<<accountNumber>>", account));
            writeObjects(webPropertyUrl, WebProperty.class);
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
        InputStream instream = null;

        try {
            List<Object> streamedObjects = new ArrayList<Object>();
            instream = new StreamBuilder(url).getInstream();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(instream);
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY) {
                    continue;
                }
                // Read an object and write it to Database
                Object object = objectMapper.readValue(jParser, cl);
                DAO.writeToDatabase(object);

                if (cl.getName() == "beans.WebProperty") {
                    writeComparisionWebProperties((WebProperty) object);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in writeObjects");
            System.out.println(url);
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                System.out.println("Error while closing stream in writeObjects");
                System.out.println(url);
                e.printStackTrace();
            }
        }

    }

    /**
     * Writes the comparison web properties for a given web property
     * 
     * @param webProperty
     *            - the WebProperty object whose comparison Web Properties are to be written to the database
     * @throws Exception
     *             - if connection is null and Exception is thrown from DAO.writeToDatabase
     */
    private void writeComparisionWebProperties(WebProperty webProperty) throws Exception {
        for (RankSourceInfo rankSourceInfo : webProperty.getRankSourceInfo()) {
            for (ComparisonWebProperty comparisonWebProperty : rankSourceInfo.getComparisonWebProperties()) {
                DAO.writeToDatabase(comparisonWebProperty);
            }
        }
    }

}
