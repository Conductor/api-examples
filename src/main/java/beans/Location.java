package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by anihalani on 6/9/15.
 * Location class to map JSON objects returned from Location endpoint
 */

@JsonPropertyOrder({ "locationId", "description" })
public class Location {

    private int locationId;
    private String description;

    public Location(int locationId, String description) {
        this.locationId = locationId;
        this.description = description;
    }

    public Location() {
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
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Location location = (Location) o;

        if (locationId != location.locationId)
            return false;
        return !(description != null ? !description.equals(location.description) : location.description != null);

    }

    @Override
    public int hashCode() {
        int result = locationId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
