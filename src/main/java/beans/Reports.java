package beans;

/**
 * Created by anihalani on 6/9/15.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "Current" })
public class Reports {

    @JsonProperty("CURRENT")
    private beans.Current Current;

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
    @JsonProperty("CURRENT")
    public void setCurrent(beans.Current current) {
        this.Current = current;
    }

    public Reports withCURRENT(beans.Current current) {
        this.Current = current;
        return this;
    }

}
