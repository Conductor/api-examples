package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/15/15.
 * TrackedSearch Class to map JSON data returned from TrackedSearch endPoint
 */

@JsonPropertyOrder({ "isActive", "trackedSearchId", "preferredUrl", "queryPhrase", "locationId", "rankSourceId",
        "deviceId" })
public class TrackedSearch {

    private boolean active;
    private int trackedSearchId;
    private String preferredUrl;
    private String queryPhrase;
    private int locationId;
    private int rankSourceId;
    private int deviceId;
    private int webPropertyId;

    /**
     *
     * @return The active
     */
    @JsonProperty("isActive")
    public boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     *            The active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * @return The trackedSearchId
     */
    @JsonProperty("trackedSearchId")
    public int getTrackedSearchId() {
        return trackedSearchId;
    }

    /**
     *
     * @param trackedSearchId
     *            The trackedSearchId
     */
    public void setTrackedSearchId(int trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
    }

    /**
     *
     * @return The preferredUrl
     */
    @JsonProperty("preferredUrl")
    public String getPreferredUrl() {
        return preferredUrl;
    }

    /**
     *
     * @param preferredUrl
     *            The preferredUrl
     */
    public void setPreferredUrl(String preferredUrl) {
        this.preferredUrl = preferredUrl;
    }

    /**
     *
     * @return The queryPhrase
     */
    @JsonProperty("queryPhrase")
    public String getQueryPhrase() {
        return queryPhrase;
    }

    /**
     *
     * @param queryPhrase
     *            The queryPhrase
     */
    public void setQueryPhrase(String queryPhrase) {
        this.queryPhrase = queryPhrase;
    }

    /**
     *
     * @return The locationId
     */
    @JsonProperty("locationId")
    public int getLocationId() {
        return locationId;
    }

    /**
     *
     * @param locationId
     *            The locationId
     */
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    /**
     *
     * @return The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public int getRankSourceId() {
        return rankSourceId;
    }

    /**
     *
     * @param rankSourceId
     *            The rankSourceId
     */
    public void setRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
    }

    /**
     *
     * @return The deviceId
     */
    @JsonProperty("deviceId")
    public int getDeviceId() {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     *            The deviceId
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Return the tracked search fro teh current tracked search
     * 
     * @return the Web Property Id for the current tracked search
     */
    public int getWebPropertyId() {
        return webPropertyId;
    }

    /**
     * Set the web property id for the current tracked search
     * 
     * @param webPropertyId
     *            - the Web Property Id to be set
     */
    public void setWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TrackedSearch that = (TrackedSearch) o;

        if (active != that.active)
            return false;
        if (trackedSearchId != that.trackedSearchId)
            return false;
        if (locationId != that.locationId)
            return false;
        if (rankSourceId != that.rankSourceId)
            return false;
        if (deviceId != that.deviceId)
            return false;
        if (webPropertyId != that.webPropertyId)
            return false;
        if (preferredUrl != null ? !preferredUrl.equals(that.preferredUrl) : that.preferredUrl != null)
            return false;
        return !(queryPhrase != null ? !queryPhrase.equals(that.queryPhrase) : that.queryPhrase != null);

    }

    @Override
    public int hashCode() {
        int result = (active ? 1 : 0);
        result = 31 * result + trackedSearchId;
        result = 31 * result + (preferredUrl != null ? preferredUrl.hashCode() : 0);
        result = 31 * result + (queryPhrase != null ? queryPhrase.hashCode() : 0);
        result = 31 * result + locationId;
        result = 31 * result + rankSourceId;
        result = 31 * result + deviceId;
        result = 31 * result + webPropertyId;
        return result;
    }
}
