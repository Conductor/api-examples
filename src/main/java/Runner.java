import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import generated.Location;
import streaming.APIPathBuilder;
import streaming.StreamBuilder;
import utils.StringUtility;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by anihalani on 6/4/15.
 */
public class Runner {

    public static void main(String[] args) {
        String generatedUrl = new APIPathBuilder(StringUtility.CONDUCTOR_API_BASE_URL, StringUtility.ENDPOINT_LOCATIONS).build();
        getLocationData(generatedUrl);

    }

    /**
     * Writes the location data returned from the API endpoint to the local database
     * @param url - the generated url with the api address, location parameters, apiKey and Signature.
     */
    private static void getLocationData(String url){
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
                Location l = objectMapper.readValue(jParser, Location.class);
                l.writeToDatabase();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
