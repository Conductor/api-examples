package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/15/15.
 */

@JsonPropertyOrder({ "ranks", "webPropertyId", "trackedSearchId", "itemType", "target", "targetDomainName", "targetUrl" })
public class ClientWebPropertyRankReport {

    @JsonProperty("ranks")
    private Ranks ranks;
    @JsonProperty("webPropertyId")
    private long webPropertyId;
    @JsonProperty("trackedSearchId")
    private long trackedSearchId;
    @JsonProperty("itemType")
    private String itemType;
    @JsonProperty("target")
    private String target;
    @JsonProperty("targetDomainName")
    private String targetDomainName;
    @JsonProperty("targetUrl")
    private String targetUrl;

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
    @JsonProperty("ranks")
    public void setRanks(Ranks ranks) {
        this.ranks = ranks;
    }

    public ClientWebPropertyRankReport withRanks(Ranks ranks) {
        this.ranks = ranks;
        return this;
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
    @JsonProperty("webPropertyId")
    public void setWebPropertyId(long webPropertyId) {
        this.webPropertyId = webPropertyId;
    }

    public ClientWebPropertyRankReport withWebPropertyId(long webPropertyId) {
        this.webPropertyId = webPropertyId;
        return this;
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
    @JsonProperty("trackedSearchId")
    public void setTrackedSearchId(long trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
    }

    public ClientWebPropertyRankReport withTrackedSearchId(long trackedSearchId) {
        this.trackedSearchId = trackedSearchId;
        return this;
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
    @JsonProperty("itemType")
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public ClientWebPropertyRankReport withItemType(String itemType) {
        this.itemType = itemType;
        return this;
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
    @JsonProperty("target")
    public void setTarget(String target) {
        this.target = target;
    }

    public ClientWebPropertyRankReport withTarget(String target) {
        this.target = target;
        return this;
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
    @JsonProperty("targetDomainName")
    public void setTargetDomainName(String targetDomainName) {
        this.targetDomainName = targetDomainName;
    }

    public ClientWebPropertyRankReport withTargetDomainName(String targetDomainName) {
        this.targetDomainName = targetDomainName;
        return this;
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
    @JsonProperty("targetUrl")
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public ClientWebPropertyRankReport withTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
        return this;
    }


    @JsonPropertyOrder({
            "UNIVERSAL_RANK",
            "TRUE_RANK",
            "CLASSIC_RANK"
    })
    public class Ranks {

        @JsonProperty("UNIVERSAL_RANK")
        private long UNIVERSALRANK;
        @JsonProperty("TRUE_RANK")
        private long TRUERANK;
        @JsonProperty("CLASSIC_RANK")
        private long CLASSICRANK;

        /**
         *
         * @return
         * The UNIVERSALRANK
         */
        @JsonProperty("UNIVERSAL_RANK")
        public long getUNIVERSALRANK() {
            return UNIVERSALRANK;
        }

        /**
         *
         * @param UNIVERSALRANK
         * The UNIVERSAL_RANK
         */
        @JsonProperty("UNIVERSAL_RANK")
        public void setUNIVERSALRANK(long UNIVERSALRANK) {
            this.UNIVERSALRANK = UNIVERSALRANK;
        }

        public Ranks withUNIVERSALRANK(long UNIVERSALRANK) {
            this.UNIVERSALRANK = UNIVERSALRANK;
            return this;
        }

        /**
         *
         * @return
         * The TRUERANK
         */
        @JsonProperty("TRUE_RANK")
        public long getTRUERANK() {
            return TRUERANK;
        }

        /**
         *
         * @param TRUERANK
         * The TRUE_RANK
         */
        @JsonProperty("TRUE_RANK")
        public void setTRUERANK(long TRUERANK) {
            this.TRUERANK = TRUERANK;
        }

        public Ranks withTRUERANK(long TRUERANK) {
            this.TRUERANK = TRUERANK;
            return this;
        }

        /**
         *
         * @return
         * The CLASSICRANK
         */
        @JsonProperty("CLASSIC_RANK")
        public long getCLASSICRANK() {
            return CLASSICRANK;
        }

        /**
         *
         * @param CLASSICRANK
         * The CLASSIC_RANK
         */
        @JsonProperty("CLASSIC_RANK")
        public void setCLASSICRANK(long CLASSICRANK) {
            this.CLASSICRANK = CLASSICRANK;
        }

        public Ranks withCLASSICRANK(long CLASSICRANK) {
            this.CLASSICRANK = CLASSICRANK;
            return this;
        }
    }
}
