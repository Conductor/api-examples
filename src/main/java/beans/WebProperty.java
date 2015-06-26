package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "isActive", "rankSourceInfo", "webPropertyId", "trackedSearchList", "name" })
public class WebProperty {

    private boolean active;
    private List<RankSourceInfo> rankSourceInfo = new ArrayList<RankSourceInfo>();
    private int webPropertyId;
    private String trackedSearchList;
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
     * @return The active
     */
    @JsonProperty("isActive")
    public boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     *            The active
     */
    public void setActive(boolean active) {
        this.active = active;
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
    public void setRankSourceInfo(List<RankSourceInfo> rankSourceInfo) {
        this.rankSourceInfo = rankSourceInfo;
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
    public void setTrackedSearchList(String trackedSearchList) {
        this.trackedSearchList = trackedSearchList;
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

}