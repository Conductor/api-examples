package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/25/15.
 * Client Web Property Rank Report Class to map JSON data returned from Rank Report endpoint
 */

@JsonPropertyOrder({ "ranks", "webPropertyId", "trackedSearchId", "itemType", "target", "targetDomainName", "targetUrl" })
public class ClientWebPropertyRankReport {

    private Ranks ranks;
    private long webPropertyId;
    private long trackedSearchId;
    private String itemType;
    private String target;
    private String targetDomainName;
    private String targetUrl;

    private String endDate;

    /**
     *
     * @return The ranks
     */
    @JsonProperty("ranks")
    public Ranks getRanks() {
        return ranks;
    }

    /**
     *
     * @param ranks
     *            The ranks
     */
    public void setRanks(Ranks ranks) {
        this.ranks = ranks;
    }

    /**
     *
     * @return The webPropertyId
     */
    @JsonProperty("webPropertyId")
    public long getWebPropertyId() {
        return webPropertyId;
    }

    /**
     *
     * @param webPropertyId
     *            The webPropertyId
     */
    public void setWebPropertyId(long webPropertyId) {
        this.webPropertyId = webPropertyId;
    }

    /**
     *
     * @return The trackedSearchId
     */
    @JsonProperty("trackedSearchId")
    public long getTrackedSearchId() {
        return trackedSearchId;
    }

    /**
     *
     * @param trackedSearchId
     *            The trackedSearchId
     */
    public void setTrackedSearchId(long trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
    }

    /**
     *
     * @return The itemType
     */
    @JsonProperty("itemType")
    public String getItemType() {
        return itemType;
    }

    /**
     *
     * @param itemType
     *            The itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     *
     * @return The target
     */
    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    /**
     *
     * @param target
     *            The target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     *
     * @return The targetDomainName
     */
    @JsonProperty("targetDomainName")
    public String getTargetDomainName() {
        return targetDomainName;
    }

    /**
     *
     * @param targetDomainName
     *            The targetDomainName
     */
    public void setTargetDomainName(String targetDomainName) {
        this.targetDomainName = targetDomainName;
    }

    /**
     *
     * @return The targetUrl
     */
    @JsonProperty("targetUrl")
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     *
     * @param targetUrl
     *            The targetUrl
     */
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonPropertyOrder({ "UNIVERSAL_RANK", "TRUE_RANK", "CLASSIC_RANK" })
    public class Ranks {

        private long UniversalRank;
        private long TrueRank;
        private long ClassicRank;

        /**
         *
         * @return The UniversalRank
         */
        @JsonProperty("UNIVERSAL_RANK")
        public long getUniversalRank() {
            return UniversalRank;
        }

        /**
         *
         * @param universalRank
         *            The UNIVERSAL_RANK
         */
        public void setUniversalRank(long universalRank) {
            this.UniversalRank = universalRank;
        }

        /**
         *
         * @return The TrueRank
         */
        @JsonProperty("TRUE_RANK")
        public long getTrueRank() {
            return TrueRank;
        }

        /**
         *
         * @param trueRank
         *            The TRUE_RANK
         */
        public void setTrueRank(long trueRank) {
            this.TrueRank = trueRank;
        }

        /**
         *
         * @return The ClassicRank
         */
        @JsonProperty("CLASSIC_RANK")
        public long getClassicRank() {
            return ClassicRank;
        }

        /**
         *
         * @param classicRank
         *            The CLASSIC_RANK
         */
        public void setClassicRank(long classicRank) {
            this.ClassicRank = classicRank;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClientWebPropertyRankReport that = (ClientWebPropertyRankReport) o;

        if (webPropertyId != that.webPropertyId)
            return false;
        if (trackedSearchId != that.trackedSearchId)
            return false;
        if (ranks != null ? !ranks.equals(that.ranks) : that.ranks != null)
            return false;
        if (itemType != null ? !itemType.equals(that.itemType) : that.itemType != null)
            return false;
        if (target != null ? !target.equals(that.target) : that.target != null)
            return false;
        if (targetDomainName != null ? !targetDomainName.equals(that.targetDomainName) : that.targetDomainName != null)
            return false;
        return !(targetUrl != null ? !targetUrl.equals(that.targetUrl) : that.targetUrl != null);

    }

    @Override
    public int hashCode() {
        int result = ranks != null ? ranks.hashCode() : 0;
        result = 31 * result + (int) (webPropertyId ^ (webPropertyId >>> 32));
        result = 31 * result + (int) (trackedSearchId ^ (trackedSearchId >>> 32));
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (targetDomainName != null ? targetDomainName.hashCode() : 0);
        result = 31 * result + (targetUrl != null ? targetUrl.hashCode() : 0);
        return result;
    }
}
