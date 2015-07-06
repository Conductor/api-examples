package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anihalani on 6/25/15.
 * Rank Source Info Class to map Rank Source information returned in Web Properties objects
 */

@JsonPropertyOrder({ "reports", "comparisonWebProperties", "rankSourceId" })
public class RankSourceInfo {

    private Reports reports;
    private List<ComparisonWebProperty> comparisonWebProperties = new ArrayList<ComparisonWebProperty>();
    private String rankSourceId;

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
    public void setReports(Reports reports) {
        this.reports = reports;
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
    public void setComparisonWebProperties(List<ComparisonWebProperty> comparisonWebProperties) {
        this.comparisonWebProperties = comparisonWebProperties;
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
    public void setRankSourceId(String rankSourceId) {
        this.rankSourceId = rankSourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RankSourceInfo that = (RankSourceInfo) o;

        if (reports != null ? !reports.equals(that.reports) : that.reports != null)
            return false;
        if (comparisonWebProperties != null ? !comparisonWebProperties.equals(that.comparisonWebProperties)
                : that.comparisonWebProperties != null)
            return false;
        return !(rankSourceId != null ? !rankSourceId.equals(that.rankSourceId) : that.rankSourceId != null);

    }

    @Override
    public int hashCode() {
        int result = reports != null ? reports.hashCode() : 0;
        result = 31 * result + (comparisonWebProperties != null ? comparisonWebProperties.hashCode() : 0);
        result = 31 * result + (rankSourceId != null ? rankSourceId.hashCode() : 0);
        return result;
    }
}
