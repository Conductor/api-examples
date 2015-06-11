package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/9/15.
 */

@JsonPropertyOrder({ "reports", "comparisonWebProperties", "rankSourceId" })
public class RankSourceInfo {

    @JsonProperty("reports")
    private Reports reports;
    @JsonProperty("comparisonWebProperties")
    private List<ComparisonWebProperty> comparisonWebProperties = new ArrayList<ComparisonWebProperty>();
    @JsonProperty("rankSourceId")
    private String rankSourceId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The reports
     */
    @JsonProperty("reports")
    public Reports getReports() {
        return reports;
    }

    /**
     *
     * @param reports
     *            The reports
     */
    @JsonProperty("reports")
    public void setReports(Reports reports) {
        this.reports = reports;
    }

    public RankSourceInfo withReports(Reports reports) {
        this.reports = reports;
        return this;
    }

    /**
     *
     * @return The comparisonWebProperties
     */
    @JsonProperty("comparisonWebProperties")
    public List<ComparisonWebProperty> getComparisonWebProperties() {
        return comparisonWebProperties;
    }

    /**
     *
     * @param comparisonWebProperties
     *            The comparisonWebProperties
     */
    @JsonProperty("comparisonWebProperties")
    public void setComparisonWebProperties(List<ComparisonWebProperty> comparisonWebProperties) {
        this.comparisonWebProperties = comparisonWebProperties;
    }

    public RankSourceInfo withComparisonWebProperties(List<ComparisonWebProperty> comparisonWebProperties) {
        this.comparisonWebProperties = comparisonWebProperties;
        return this;
    }

    /**
     *
     * @return The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public String getRankSourceId() {
        return rankSourceId;
    }

    /**
     *
     * @param rankSourceId
     *            The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public void setRankSourceId(String rankSourceId) {
        this.rankSourceId = rankSourceId;
    }

    public RankSourceInfo withRankSourceId(String rankSourceId) {
        this.rankSourceId = rankSourceId;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public RankSourceInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
