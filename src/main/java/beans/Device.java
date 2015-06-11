package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by anihalani on 6/8/15.
 */

@JsonPropertyOrder({ "description", "deviceId" })
public class Device {

    @JsonProperty("description")
    private String description;
    @JsonProperty("deviceId")
    private int deviceId;

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

    public Device withDescription(String description) {
        this.description = description;
        return this;
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
    @JsonProperty("deviceId")
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Device withDeviceId(int deviceId) {
        this.deviceId = deviceId;
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

        Device device = (Device) o;

        if (deviceId != device.deviceId)
            return false;
        return description.equals(device.description);

    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + deviceId;
        return result;
    }
}