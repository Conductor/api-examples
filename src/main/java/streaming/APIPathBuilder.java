package streaming;

import utils.Util;

/**
 * Created by anihalani on 5/31/15. APIPathBuilder class to generate the URL, manage endpoints and other parameters of
 * the URL
 */
public class APIPathBuilder {
    private String baseUrl;

    public APIPathBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Builds and return a complete url including the api baseUrl, endpoint paramter, apiKey and the Shared secret
     * 
     * @return The complete usable API url with apiKey and Signature
     * @param completeUrl
     *            - Url with all the endpoints and correspoinding values
     */
    public String addKeyAndSignature(String completeUrl) {
        return Util.generateRecoServiceUrl(completeUrl);
    }

    /**
     * Adds a endpoint to the url
     * 
     * @param endPoint
     *            - The endpoint name (eg - "locations", "devices, "web-properties", etc")
     * @param endPointValue
     *            - The value of endPoint parameter
     * @return The url that is a combination of baseUrl and endpoint
     */
    public String buildWithEndpoint(String endPoint, String endPointValue) {
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("/v3");
        if (endPoint != null && !endPoint.equals("")) {
            builder.append("/").append(endPoint);
        }
        if (endPointValue != null && !endPointValue.equals("")) {
            builder.append("/").append(endPointValue);
        }
        return (builder.toString());
    }

    /**
     *
     * @param url
     *            - The incomplete URl to which the parameters are to be added
     * @param endpoint
     *            - The endpoint name (eg - "locations", "devices, "web-properties", etc")
     * @param parameterValue
     *            - The value of the endpoint parameter (eg: '1')
     * @return The string with endpoint and its value included
     */
    public String addEndPointWithValue(String url, String endpoint, String parameterValue) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("/").append(endpoint);
        if (parameterValue != null && !parameterValue.equals("")) {
            builder.append("/").append(parameterValue);
        }
        return builder.toString();
    }

}
