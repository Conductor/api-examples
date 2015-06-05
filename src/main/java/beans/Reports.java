package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/25/15.
 * Reports Class to map Reports data returned within Web Properties objects
 */

@JsonPropertyOrder({ "timePeriod" })
public class Reports {

    private TimePeriod timePeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reports reports = (Reports) o;

        return !(timePeriod != null ? !timePeriod.equals(reports.timePeriod) : reports.timePeriod != null);

    }

    @Override
    public int hashCode() {
        return timePeriod != null ? timePeriod.hashCode() : 0;
    }

    /**
     *
     * @return The timePeriod
     */
    @JsonProperty("CURRENT")
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    /**
     *
     * @param timePeriod
     *            The timePeriod
     */
    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

}
