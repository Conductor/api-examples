package utils;

/**
 * Created by anihalani on 5/31/15.
 */
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Miscellaneous utilities used by the reco client.
 *
 * @author anihalani
 *
 */
public class Util {
    public static final String PROPS_FILE = "conductorAPI.properties";

    /**
     * Read config properties file.
     * @return {@code Properties} object containing all detected config params
     */
    public static Properties readProperties(final String propsFile) {
        final Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = Util.class.getClassLoader().getResourceAsStream(propsFile);
            if(inputStream == null) {
                throw new IllegalArgumentException("Cannot read properties file: " + propsFile);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e) { /* ignore */ }
            }
        }
        return properties;
    }

    /**
     * Given a base api end point url and an {@code apiKey} and {@code sharedSecret}, generate signed api end point url.
     */
    public static String generateRecoServiceUrl(final String apiEndpointUrl) {
        try {
            Properties properties = readProperties(PROPS_FILE);
            return apiEndpointUrl
                    + "?apiKey="
                    + properties.getProperty("API_KEY")
                    + "&sig="
                    + generateSignature(properties.getProperty("API_KEY"), properties.getProperty("SHARED_SECRET"));
        } catch (NoSuchAlgorithmException e) {
            // nothing we can do about this
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate api key signature as per http://developers.conductor.com/docs/Searchlight_API_Authentication_Overview
     */
    private static String generateSignature(final String apiKey, final String sharedSecret)
            throws NoSuchAlgorithmException {
        final long curTimeEpochSeconds = Math.round(System.currentTimeMillis() / 1000.0);
        final String stringToHash = apiKey + sharedSecret + curTimeEpochSeconds;
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] digestBytes = md.digest(stringToHash.getBytes());
        // org.apache.commons.codec.binary provides similar functionality for hex encoding but can lead to dependency conflicts in large projects with many transitive dependencies
        final String hexEncoded = String.format("%x", new BigInteger(1, digestBytes));
        return hexEncoded;
    }

}
