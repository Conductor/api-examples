package streaming;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import generated.Location;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by anihalani on 6/5/15.
 */
public class StreamBuilderTest {

    @Test
    public void testStreamBuilder() {
        String generatedUrl = new APIPathBuilder("https://api.conductor.com", "locations").build();
        InputStream instream = new StreamBuilder(generatedUrl).getInstream();

        // Check if it is null
        assertNotNull(instream);
        // Check if a list of Location objects is being returned
        List<Location> locationList = mapLocationObject(instream);
        assertNotNull(locationList);
        // Check if the list has location objects
        assertTrue(locationList.size() > 0);
    }

    private List<Location> mapLocationObject(InputStream is) {
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
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}