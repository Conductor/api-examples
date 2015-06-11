package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/9/15.
 */

@JsonPropertyOrder({ "startDate", "endDate", "webPropertySearchVolumeReport", "webPropertyRankReport", "timePeriodId" })
public class Current {

    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("webPropertySearchVolumeReport")
    private String webPropertySearchVolumeReport;
    @JsonProperty("webPropertyRankReport")
    private String webPropertyRankReport;
    @JsonProperty("timePeriodId")
    private String timePeriodId;

    /**
     *
     * @return The startDate
     */
    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     *            The startDate
     */
    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Current withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     *
     * @return The endDate
     */
    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     *            The endDate
     */
    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Current withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     *
     * @return The webPropertySearchVolumeReport
     */
    @JsonProperty("webPropertySearchVolumeReport")
    public String getWebPropertySearchVolumeReport() {
        return webPropertySearchVolumeReport;
    }

    /**
     *
     * @param webPropertySearchVolumeReport
     *            The webPropertySearchVolumeReport
     */
    @JsonProperty("webPropertySearchVolumeReport")
    public void setWebPropertySearchVolumeReport(String webPropertySearchVolumeReport) {
        this.webPropertySearchVolumeReport = webPropertySearchVolumeReport;
    }

    public Current withWebPropertySearchVolumeReport(String webPropertySearchVolumeReport) {
        this.webPropertySearchVolumeReport = webPropertySearchVolumeReport;
        return this;
    }

    /**
     *
     * @return The webPropertyRankReport
     */
    @JsonProperty("webPropertyRankReport")
    public String getWebPropertyRankReport() {
        return webPropertyRankReport;
    }

    /**
     *
     * @param webPropertyRankReport
     *            The webPropertyRankReport
     */
    @JsonProperty("webPropertyRankReport")
    public void setWebPropertyRankReport(String webPropertyRankReport) {
        this.webPropertyRankReport = webPropertyRankReport;
    }

    public Current withWebPropertyRankReport(String webPropertyRankReport) {
        this.webPropertyRankReport = webPropertyRankReport;
        return this;
    }

    /**
     *
     * @return The timePeriodId
     */
    @JsonProperty("timePeriodId")
    public String getTimePeriodId() {
        return timePeriodId;
    }

    /**
     *
     * @param timePeriodId
     *            The timePeriodId
     */
    @JsonProperty("timePeriodId")
    public void setTimePeriodId(String timePeriodId) {
        this.timePeriodId = timePeriodId;
    }

    public Current withTimePeriodId(String timePeriodId) {
        this.timePeriodId = timePeriodId;
        return this;
    }
}
