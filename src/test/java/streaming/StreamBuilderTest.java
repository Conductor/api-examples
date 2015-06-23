package streaming;

import beans.Location;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by anihalani on 6/5/15.
 * CLass to test StreamBuilder
 */
public class StreamBuilderTest {
    String generatedUrl;
    InputStream instream;
    APIPathBuilder pathBuilder;
    private static final String ENDPOINT_LOCATIONS = "locations";

    @Before
    public void setup() {
        pathBuilder = new APIPathBuilder("https://api.conductor.com");
        generatedUrl = pathBuilder.buildWithEndpoint(ENDPOINT_LOCATIONS, null);
        generatedUrl = pathBuilder.addKeyAndSignature(generatedUrl);
        instream = new StreamBuilder(generatedUrl).getInstream();
    }

    /**
     * Verify if correct input stream is returned
     * 
     * @throws Exception
     *             - JSONParse Exception while mapping objects
     */
    @Test
    public void testStreamBuilder() throws Exception {
        // Check if it is null
        assertNotNull(instream);
        // Check if a list of Location objects is being returned

        List<Location> locationList = mapLocationObject(instream);
        assertNotNull(locationList);
        // Check if the list has location objects
        assertTrue(locationList.size() > 0);
    }

    /**
     * Verify that an exception is thrown if incorrect objects are tried to map
     * 
     * @throws Exception
     *             - JSONParseException during incorrect mapping
     */
    @Test(expected = JsonParseException.class)
    public void checkInValidStreams() throws Exception {
        generatedUrl = new APIPathBuilder("https://api.conductor.com").buildWithEndpoint(ENDPOINT_LOCATIONS, null);
        instream = new StreamBuilder(generatedUrl).getInstream();
        // Check if a list of Location objects is being returned
        List<Location> locationList = mapLocationObject(instream);
    }

    /**
     *
     * @param is
     *            - Input Stream
     * @return List of locations returned from API endpoint (input stream)
     * @throws Exception
     *             IOException, jsonParseException
     */
    private List<Location> mapLocationObject(InputStream is) throws Exception {
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(is);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Location> locations = new ArrayList<>();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY)
                    continue;
                // map the json object to a Location object and return it
                locations.add(objectMapper.readValue(jParser, Location.class));
            }
            return locations;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}