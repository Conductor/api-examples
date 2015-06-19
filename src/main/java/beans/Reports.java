package beans;

/**
 * Created by anihalani on 6/9/15.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "Current" })
public class Reports {

    private beans.Current Current;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Reports reports = (Reports) o;

        return !(Current != null ? !Current.equals(reports.Current) : reports.Current != null);

    }

    @Override
    public int hashCode() {
        return Current != null ? Current.hashCode() : 0;
    }

    /**
     *
     * @return The Current
     */
    @JsonProperty("CURRENT")
    public beans.Current getCurrent() {
        return Current;
    }

    /**
     *
     * @param current
     *            The Current
     */
    public void setCurrent(beans.Current current) {
        this.Current = current;
    }

}
