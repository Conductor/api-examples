package beans;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/12/15.
 */

@JsonPropertyOrder({
        "isActive",
        "trackedSearchId",
        "preferredUrl",
        "queryPhrase",
        "locationId",
        "rankSourceId",
        "deviceId"
})
public class TrackedSearch {

    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("trackedSearchId")
    private int trackedSearchId;
    @JsonProperty("preferredUrl")
    private String preferredUrl;
    @JsonProperty("queryPhrase")
    private String queryPhrase;
    @JsonProperty("locationId")
    private int locationId;
    @JsonProperty("rankSourceId")
    private int rankSourceId;
    @JsonProperty("deviceId")
    private int deviceId;

    private int webPropertyId;

    /**
     *
     * @return
     * The isActive
     */
    @JsonProperty("isActive")
    public boolean isIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The isActive
     */
    @JsonProperty("isActive")
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public TrackedSearch withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     *
     * @return
     * The trackedSearchId
     */
    @JsonProperty("trackedSearchId")
    public int getTrackedSearchId() {
        return trackedSearchId;
    }

    /**
     *
     * @param trackedSearchId
     * The trackedSearchId
     */
    @JsonProperty("trackedSearchId")
    public void setTrackedSearchId(int trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
    }

    public TrackedSearch withTrackedSearchId(int trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
        return this;
    }

    /**
     *
     * @return
     * The preferredUrl
     */
    @JsonProperty("preferredUrl")
    public String getPreferredUrl() {
        return preferredUrl;
    }

    /**
     *
     * @param preferredUrl
     * The preferredUrl
     */
    @JsonProperty("preferredUrl")
    public void setPreferredUrl(String preferredUrl) {
        this.preferredUrl = preferredUrl;
    }

    public TrackedSearch withPreferredUrl(String preferredUrl) {
        this.preferredUrl = preferredUrl;
        return this;
    }

    /**
     *
     * @return
     * The queryPhrase
     */
    @JsonProperty("queryPhrase")
    public String getQueryPhrase() {
        return queryPhrase;
    }

    /**
     *
     * @param queryPhrase
     * The queryPhrase
     */
    @JsonProperty("queryPhrase")
    public void setQueryPhrase(String queryPhrase) {
        this.queryPhrase = queryPhrase;
    }

    public TrackedSearch withQueryPhrase(String queryPhrase) {
        this.queryPhrase = queryPhrase;
        return this;
    }

    /**
     *
     * @return
     * The locationId
     */
    @JsonProperty("locationId")
    public int getLocationId() {
        return locationId;
    }

    /**
     *
     * @param locationId
     * The locationId
     */
    @JsonProperty("locationId")
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public TrackedSearch withLocationId(int locationId) {
        this.locationId = locationId;
        return this;
    }

    /**
     *
     * @return
     * The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public int getRankSourceId() {
        return rankSourceId;
    }

    /**
     *
     * @param rankSourceId
     * The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public void setRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
    }

    public TrackedSearch withRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
        return this;
    }

    /**
     *
     * @return
     * The deviceId
     */
    @JsonProperty("deviceId")
    public int getDeviceId() {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     * The deviceId
     */
    @JsonProperty("deviceId")
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public TrackedSearch withDeviceId(int deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    /**
     * Return the tracked search fro teh current tracked search
     * @return the Web Property Id for the current tracked search
     */
    public int getWebPropertyId() {
        return webPropertyId;
    }

    /**
     * Set the web property id for the current tracked search
     * @param webPropertyId - the Web Property Id to be set
     */
    public void setWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
    }
}



