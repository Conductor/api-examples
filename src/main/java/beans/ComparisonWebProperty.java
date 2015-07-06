package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/9/15.
 * Comparison Web Property class to map JSON object of type Comparison Web Properties
 */

@JsonPropertyOrder({ "label", "name", "webPropertyId" })
public class ComparisonWebProperty {

    private String label;
    private String name;
    private int webPropertyId;
    private int accountId;

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
    public void setLabel(String label) {
        this.label = label;
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
    public void setName(String name) {
        this.name = name;
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
    public void setWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
    }

    /**
     * Getter for AccountId
     * 
     * @return the account id associated with the current associated web property
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * Setter for Account Id
     * 
     * @param accountId
     *            - the account id for the comparison web property
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
        if (accountId != that.accountId)
            return false;
        if (label != null ? !label.equals(that.label) : that.label != null)
            return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + webPropertyId;
        result = 31 * result + accountId;
        return result;
    }
}