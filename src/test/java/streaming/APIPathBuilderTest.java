package streaming;

import org.junit.Test;
import utils.StringUtility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by anihalani on 6/3/15.
 */
public class APIPathBuilderTest {

    @Test
    public void testBaseUrl(){
        APIPathBuilder path = new APIPathBuilder(StringUtility.CONDUCTOR_API_BASE_URL, StringUtility.ENDPOINT_LOCATIONS);
        String url = path.build();
        // check if url is null
        assertNotNull(url);
        // check if correct expected url is being returned
        assertEquals(true, url.contains("api.conductor.com/v3/locations?apiKey=obwijyt9sjplrzwk8c9nrmb276tk5cyym9orp1l4&sig="));
    }

}