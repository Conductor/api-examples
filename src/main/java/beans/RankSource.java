package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by anihalani on 6/8/15.
 */

@JsonPropertyOrder({ "baseDomain", "description", "rankSourceId", "name" })
public class RankSource {

    @JsonProperty("baseDomain")
    private String baseDomain;
    @JsonProperty("description")
    private String description;
    @JsonProperty("rankSourceId")
    private int rankSourceId;
    @JsonProperty("name")
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
    @JsonProperty("baseDomain")
    public void setBaseDomain(String baseDomain) {
        this.baseDomain = baseDomain;
    }

    public RankSource withBaseDomain(String baseDomain) {
        this.baseDomain = baseDomain;
        return this;
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
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public RankSource withDescription(String description) {
        this.description = description;
        return this;
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
    @JsonProperty("rankSourceId")
    public void setRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
    }

    public RankSource withRankSourceId(int rankSourceId) {
        this.rankSourceId = rankSourceId;
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

    public RankSource withName(String name) {
        this.name = name;
        return this;
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
        if (!baseDomain.equals(that.baseDomain))
            return false;
        if (!description.equals(that.description))
            return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = baseDomain.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + rankSourceId;
        result = 31 * result + name.hashCode();
        return result;
    }
}