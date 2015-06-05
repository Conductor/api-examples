package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by anihalani on 6/25/15.
 * Rank Source Class to map JSON data returned by Rank Source endpoint
 */
@JsonPropertyOrder({ "baseDomain", "description", "rankSourceId", "name" })
public class RankSource {

    private String baseDomain;
    private String description;
    private int rankSourceId;
    private String name;

    /**
     *
     * @return The baseDomain
     */
    @JsonProperty("baseDomain")
    public String getBaseDomain() {
        return baseDomain;
    }

    /**
     *
     * @param baseDomain
     *            The baseDomain
     */
    public void setBaseDomain(String baseDomain) {
        this.baseDomain = baseDomain;
    }

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
     * @return The rankSourceId
     */
    @JsonProperty("rankSourceId")
    public int getRankSourceId() {
        return rankSourceId;
    }

    /**
     *
     * @param rankSourceId
     *            The rankSourceId
     */
    public void setRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
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

        RankSource that = (RankSource) o;

        if (rankSourceId != that.rankSourceId)
            return false;
        if (baseDomain != null ? !baseDomain.equals(that.baseDomain) : that.baseDomain != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = baseDomain != null ? baseDomain.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + rankSourceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}