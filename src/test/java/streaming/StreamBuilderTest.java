package streaming;

import beans.Location;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by anihalani on 6/5/15.
 */
public class StreamBuilderTest {
    String generatedUrl;
    InputStream instream;
    private static final String ENDPOINT_LOCATIONS = "locations";

    @Before
    public void setup(){
         generatedUrl = new APIPathBuilder("https://api.conductor.com", "locations").build(ENDPOINT_LOCATIONS);
         instream = new StreamBuilder(generatedUrl).getInstream();
    }

    @Test
    public void testStreamBuilder() {
        // Check if it is null
        assertNotNull(instream);
        // Check if a list of Location objects is being returned
        try{
            List<Location> locationList = mapLocationObject(instream);
            assertNotNull(locationList);
            // Check if the list has location objects
            assertTrue(locationList.size() > 0);
        }catch(Exception e){
            System.out.println("Test Failing! Error in testStreamBuilder!");
            e.printStackTrace();
        }
    }

    //Check if incorrect object is mapped
    @Test
    public void checkInValidStreams(){
        generatedUrl = new APIPathBuilder("https://api.conductor.com", "devices").build(ENDPOINT_LOCATIONS);
        instream = new StreamBuilder(generatedUrl).getInstream();

        // Check if a list of Location objects is being returned
        try{
            List<Location> locationList = mapLocationObject(instream);
        }catch(Exception e){
            assertEquals(e.getClass(), UnrecognizedPropertyException.class);
        }
    }

    /**
     *
     * @param is - Input Stream
     * @return List of locations returned from API endpoint (input stream)
     * @throws Exception IOException, jsonParseException
     */
    private List<Location> mapLocationObject(InputStream is) throws Exception{
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jParser = jsonFactory.createJsonParser(is);
            ObjectMapper objectMapper = new ObjectMapper();
            List<Location> locations = new ArrayList<Location>();
            // Read the json objects from stream one at a time
            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                if (jParser.getCurrentToken() == JsonToken.START_ARRAY)
                    continue;
                // map the json object to a Location object and return it
                locations.add(objectMapper.readValue(jParser, Location.class));
            }
            return locations;
        }catch (Exception e) {
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}