package streaming;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by anihalani on 6/5/15. StreamBuilder class to build the Input Stream from a given URL
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
     * @param inStream
     *            - the input stream to read from
     */
    public void setInStream(InputStream inStream) {
        this.inputStream = inStream;
    }

    /**
     * Returns the input stream
     * 
     * @return inputStream - the input stream for the api end point
     */
    public InputStream getInStream() {

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
            return new URL(url).openStream();
        } catch (IOException e) {
            System.out.println("Error in StreamBuilder.buildInStream");
            throw new RuntimeException(String.format("Unable to create InputStream from API \n %s", e));
        }
    }
}
