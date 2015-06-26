package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonPropertyOrder({ "description", "deviceId" })
public class Device {

    private String description;
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
    public void setDescription(String description) {
        this.description = description;
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
        return !(description != null ? !description.equals(device.description) : device.description != null);

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + deviceId;
        return result;
    }
}