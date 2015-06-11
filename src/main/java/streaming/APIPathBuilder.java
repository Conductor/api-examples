package streaming;

import utils.Util;

/**
 * Created by anihalani on 5/31/15.
 */
public class APIPathBuilder {
    private String baseUrl;
    private String endpointParameter;

    public APIPathBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public APIPathBuilder(String baseUrl, String endpoint) {
        this.baseUrl = baseUrl;
        this.endpointParameter = endpoint;
    }

    /**
     * Used to set the enpoint for API query.
     *
     * @param endpoint
     * @return
     */
    public APIPathBuilder setEndpoint(String endpoint) {
        this.endpointParameter = endpoint;
        return this;
    }

    /**
     * Builds and return a complete url including the api baseUrl, endpoint paramter, apiKey and the Shared secret
     * 
     * @return
     * @param endpointLocations
     */
    public String build(String endpointLocations) {
        this.endpointParameter = endpointLocations;
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("/v3/");
        builder.append(endpointParameter);
        return Util.generateRecoServiceUrl(builder.toString());

    }

}
