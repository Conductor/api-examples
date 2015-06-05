package streaming;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by anihalani on 6/3/15.
 * Tests for APIPathBuilder
 */
public class APIPathBuilderTest {
    public static final String CONDUCTOR_API_BASE_URL = "https://api.conductor.com";
    public static final String ENDPOINT_LOCATIONS = "locations";

    @Test
    public void testBaseUrl(){
        APIPathBuilder path = new APIPathBuilder(CONDUCTOR_API_BASE_URL);
        String url = path.buildWithEndpoint(ENDPOINT_LOCATIONS, null);
        // check if url is null
        assertNotNull(url);
        // check if correct expected url is being returned
        assertEquals("https://api.conductor.com/v3/locations", url);
    }

    @Test
    public void testAPIKeyAndSignature(){
        APIPathBuilder path = new APIPathBuilder(CONDUCTOR_API_BASE_URL);
        String url = path.buildWithEndpoint(ENDPOINT_LOCATIONS, null);
        url = path.addKeyAndSignature(url);
        // check if url is null
        assertNotNull(url);
        // check if correct expected url is being returned
        assertEquals(true, url.contains("api.conductor.com/v3/locations?apiKey=obwijyt9sjplrzwk8c9nrmb276tk5cyym9orp1l4&sig="));
    }
}