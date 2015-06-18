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
     * @param completeUrl
     */
    public String addKeyAndSignature(String completeUrl) {
        StringBuilder builder = new StringBuilder(completeUrl);
        return Util.generateRecoServiceUrl(builder.toString());
    }

    /**
     * Adds a signature apiKey and the Shared secret
     *
     * @return
     * @param endpoint
     */
    public String build(String endpoint) {
        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append("/v3/");
        builder.append(endpoint);
        return (builder.toString());
    }

    public String addEndPointWithValue(String url, String endpoint, String parameterValue){
        StringBuilder builder = new StringBuilder(url);
        builder.append("/"+endpoint+"/"+parameterValue);
      //  builder.a
        return builder.toString();
    }

}
