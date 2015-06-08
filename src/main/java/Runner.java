import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DeviceDAO;
import dao.LocationDAO;
import dao.RankedSourceDAO;
import beans.Device;
import beans.Location;
import beans.RankSource;
import streaming.APIPathBuilder;
import streaming.StreamBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anihalani on 6/4/15.
 */
public class Runner {

    public static final String CONDUCTOR_API_BASE_URL = "https://api.conductor.com";
    public static final String ENDPOINT_LOCATIONS = "locations";
    public static final String ENDPOINT_RANK_SOURCES = "rank-sources";
    public static final String ENDPOINT_DEVICES = "devices";

    public static void main(String[] args) {
         String locationsUrl = new APIPathBuilder(CONDUCTOR_API_BASE_URL,
         ENDPOINT_LOCATIONS).build();
         getLocationData(locationsUrl);

         String rankSourceUrl = new APIPathBuilder(CONDUCTOR_API_BASE_URL,
         ENDPOINT_RANK_SOURCES).build();
         getRankSourceData(rankSourceUrl);

        String devicesUrl = new APIPathBuilder(CONDUCTOR_API_BASE_URL, ENDPOINT_DEVICES)
                .build();
        getDeviceData(devicesUrl);
    }

    /**
     * Writes the location data returned from the API endpoint to the local database
     * 
     * @param url
     *            - the beans url with the api address, endpoint parameter, apiKey and Signature.
     */
    private static void getLocationData(String url) {
        InputStream instream = null;

        try {
            instream = new StreamBuilder(url).getInstream();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(instream);
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY) {
                    continue;
                }
                // Read a Location and write it to Database
                Location location = objectMapper.readValue(jParser, Location.class);
                LocationDAO.writeToDatabase(location);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                System.out.println("Error while closing stream in Runner.getLocationData");
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the rank_source data returned from the API endpoint to the local database
     * 
     * @param url
     *            - the beans url with the api address, endpoint parameter, apiKey and Signature.
     */
    private static void getRankSourceData(String url) {
        InputStream instream = null;

        try {
            instream = new StreamBuilder(url).getInstream();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(instream);
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY)
                    continue;
                // Read a Location and write it to Database
                RankSource rankSource = objectMapper.readValue(jParser, RankSource.class);
                RankedSourceDAO.writeToDatabase(rankSource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                System.out.println("Error while closing stream in Runner.getSourceData");
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the device data returned from the API endpoint to the local database
     * 
     * @param url
     *            - the beans url with the api address, endpoint parameter, apiKey and Signature.
     */
    private static void getDeviceData(String url) {
        InputStream instream = null;

        try {
            instream = new StreamBuilder(url).getInstream();
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(instream);
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY)
                    continue;
                // Read a Location and write it to Database
                Device device = objectMapper.readValue(jParser, Device.class);
                DeviceDAO.writeToDatabase(device);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                System.out.println("Error while closing stream in Runner.getDeviceData");
                e.printStackTrace();
            }
        }
    }
}
