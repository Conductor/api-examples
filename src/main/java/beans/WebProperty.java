package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anihalani on 6/9/15.
 */

@JsonPropertyOrder({ "isActive", "rankSourceInfo", "webPropertyId", "trackedSearchList", "name" })
public class WebProperty {

    @JsonProperty("isActive")
    private boolean isActive;
    @JsonProperty("rankSourceInfo")
    private List<RankSourceInfo> rankSourceInfo = new ArrayList<RankSourceInfo>();
    @JsonProperty("webPropertyId")
    private int webPropertyId;
    @JsonProperty("trackedSearchList")
    private String trackedSearchList;
    @JsonProperty("name")
    private String name;

    private int accountId;

    /**
     *
     * @return the Accound Id
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     *
     * @param accountId
     *            - the Account id for the web property
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     *
     * @return The isActive
     */
    @JsonProperty("isActive")
    public boolean isActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     *            The isActive
     */
    @JsonProperty("isActive")
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public WebProperty withIsActive(boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    /**
     *
     * @return The rankSourceInfo
     */
    @JsonProperty("rankSourceInfo")
    public List<RankSourceInfo> getRankSourceInfo() {
        return rankSourceInfo;
    }

    /**
     *
     * @param rankSourceInfo
     *            The rankSourceInfo
     */
    @JsonProperty("rankSourceInfo")
    public void setRankSourceInfo(List<RankSourceInfo> rankSourceInfo) {
        this.rankSourceInfo = rankSourceInfo;
    }

    public WebProperty withRankSourceInfo(List<RankSourceInfo> rankSourceInfo) {
        this.rankSourceInfo = rankSourceInfo;
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

    public WebProperty withWebPropertyId(int webPropertyId) {
        this.webPropertyId = webPropertyId;
        return this;
    }

    /**
     *
     * @return The trackedSearchList
     */
    @JsonProperty("trackedSearchList")
    public String getTrackedSearchList() {
        return trackedSearchList;
    }

    /**
     *
     * @param trackedSearchList
     *            The trackedSearchList
     */
    @JsonProperty("trackedSearchList")
    public void setTrackedSearchList(String trackedSearchList) {
        this.trackedSearchList = trackedSearchList;
    }

    public WebProperty withTrackedSearchList(String trackedSearchList) {
        this.trackedSearchList = trackedSearchList;
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

    public WebProperty withName(String name) {
        this.name = name;
        return this;
    }
}