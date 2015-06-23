package streaming;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anihalani on 6/5/15.
 */
public class StreamBuilder {

    InputStream inputStream;

    public StreamBuilder(String url) {
        this.inputStream = buildInStream(url);
    }

    public StreamBuilder() {
    }

    /**
     * Sets the inputStream property of class StreamBuilder
     *
     * @param instream
     *            - the input stream to read from
     */
    public void setInstream(InputStream instream) {
        this.inputStream = instream;
    }

    /**
     * Returns the input stream
     * 
     * @return inputStream - the input stream for the api end point
     */
    public InputStream getInstream() {

        return inputStream;
    }

    /**
     *
     * @param url
     *            - the complete url for the API endpoint to be accessed
     * @return the InputStream object which can be used to get the json response
     */
    public InputStream buildInStream(String url) throws RuntimeException {

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();
            return entity.getContent();

        } catch (IOException e) {
            System.out.println("Error in StreamBuilder.buildInStream");
            throw new RuntimeException(String.format("Unable to create InputStream from API \n %s", e));
        }
    }
}
