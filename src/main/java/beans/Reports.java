package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/25/15.
 * Reports Class to map Reports data returned from with WebProperties objects
 */

@JsonPropertyOrder({ "TimePeriod" })
public class Reports {

    private TimePeriod TimePeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reports reports = (Reports) o;

        return !(TimePeriod != null ? !TimePeriod.equals(reports.TimePeriod) : reports.TimePeriod != null);

    }

    @Override
    public int hashCode() {
        return TimePeriod != null ? TimePeriod.hashCode() : 0;
    }

    /**
     *
     * @return The TimePeriod
     */
    @JsonProperty("CURRENT")
    public TimePeriod getTimePeriod() {
        return TimePeriod;
    }

    /**
     *
     * @param timePeriod
     *            The TimePeriod
     */
    public void setTimePeriod(TimePeriod timePeriod) {
        this.TimePeriod = timePeriod;
    }

}
