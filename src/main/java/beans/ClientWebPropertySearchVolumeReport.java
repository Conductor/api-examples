package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anihalani on 6/25/15.
 * Client Web Property Search Volume Report Class to map JSON data returned from searchVolume endPoint
 */

@JsonPropertyOrder({ "averageVolume", "trackedSearchId", "volumeItems" })
public class ClientWebPropertySearchVolumeReport {

    private long averageVolume;
    private long trackedSearchId;



     private List<VolumeItem> volumeItems = new ArrayList<>();
    //private Object volumeItems;

    /**
     *
     * @return The averageVolume
     */
    @JsonProperty("averageVolume")
    public long getAverageVolume() {
        return averageVolume;
    }

    /**
     *
     * @param averageVolume
     *            The averageVolume
     */
    public void setAverageVolume(long averageVolume) {
        this.averageVolume = averageVolume;
    }

    /**
     *
     * @return The trackedSearchId
     */
    @JsonProperty("trackedSearchId")
    public long getTrackedSearchId() {
        return trackedSearchId;
    }

    /**
     *
     * @param trackedSearchId
     *            The trackedSearchId
     */
    public void setTrackedSearchId(long trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
    }

    /**
     *
     * @return The volumeItems
     */
    @JsonProperty("volumeItems")
    public List<VolumeItem> getVolumeItems() {
        return volumeItems;
    }

    /**
     *
     * @param volumeItems
     *            The volumeItems
     */
    public void setVolumeItems(List<VolumeItem> volumeItems) {
        this.volumeItems = volumeItems;
    }

}