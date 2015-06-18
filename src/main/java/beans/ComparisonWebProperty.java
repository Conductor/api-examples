package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/9/15.
 */

@JsonPropertyOrder({ "label", "name", "webPropertyId" })
public class ComparisonWebProperty {

    @JsonProperty("label")
    private String label;
    @JsonProperty("name")
    private String name;
    @JsonProperty("webPropertyId")
    private int webPropertyId;

    private int accountId;

    /**
     * Getter for AccountId
     * @return the account id associated with the current associated web property
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Setter for Account Id
     * @param accountId - the account id for the comparison web property
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    /**
     *
     * @return The label
     */
    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     *            The label
     */
    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    public ComparisonWebProperty withLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     *
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public ComparisonWebProperty withName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return The webPropertyId
     */
    @JsonProperty("webPropertyId")
    public int getWebPropertyId() {
        return webPropertyId;
    }

    /**
     *
     * @param webPropertyId
     *            The webPropertyId
     */
    @JsonProperty("webPropertyId")
    public void setWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
    }

    public ComparisonWebProperty withWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComparisonWebProperty that = (ComparisonWebProperty) o;

        if (webPropertyId != that.webPropertyId)
            return false;
        if (label != null ? !label.equals(that.label) : that.label != null)
            return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + webPropertyId;
        return result;
    }
}