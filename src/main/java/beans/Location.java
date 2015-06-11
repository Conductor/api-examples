package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonPropertyOrder({ "locationId", "description" })
public class Location {

    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("description")
    private String description;

    public Location() {

    }

    public Location(String locationId, String description) {
        this.locationId = locationId;
        this.description = description;
    }

    /**
     *
     * @return The locationId
     */
    @JsonProperty("locationId")
    public String getLocationId() {
        return locationId;
    }

    /**
     *
     * @param locationId
     *            The locationId
     */
    @JsonProperty("locationId")
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Location withLocationId(String locationId) {
        this.locationId = locationId;
        return this;
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
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Location withDescription(String description) {
        this.description = description;
        return this;
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

        if (!locationId.equals(location.locationId))
            return false;
        return description.equals(location.description);

    }

    @Override
    public int hashCode() {
        int result = locationId.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
