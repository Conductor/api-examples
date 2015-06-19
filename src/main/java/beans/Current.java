package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by anihalani on 6/9/15.
 */

@JsonPropertyOrder({ "startDate", "endDate", "webPropertySearchVolumeReport", "webPropertyRankReport", "timePeriodId" })
public class Current {

    private String startDate;
    private String endDate;
    private String webPropertySearchVolumeReport;
    private String webPropertyRankReport;
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
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
    public void setWebPropertySearchVolumeReport(String webPropertySearchVolumeReport) {
        this.webPropertySearchVolumeReport = webPropertySearchVolumeReport;
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
    public void setWebPropertyRankReport(String webPropertyRankReport) {
        this.webPropertyRankReport = webPropertyRankReport;
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
    public void setTimePeriodId(String timePeriodId) {
        this.timePeriodId = timePeriodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Current current = (Current) o;

        if (startDate != null ? !startDate.equals(current.startDate) : current.startDate != null) return false;
        if (endDate != null ? !endDate.equals(current.endDate) : current.endDate != null) return false;
        if (webPropertySearchVolumeReport != null ? !webPropertySearchVolumeReport.equals(current.webPropertySearchVolumeReport) : current.webPropertySearchVolumeReport != null)
            return false;
        if (webPropertyRankReport != null ? !webPropertyRankReport.equals(current.webPropertyRankReport) : current.webPropertyRankReport != null)
            return false;
        return !(timePeriodId != null ? !timePeriodId.equals(current.timePeriodId) : current.timePeriodId != null);

    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (webPropertySearchVolumeReport != null ? webPropertySearchVolumeReport.hashCode() : 0);
        result = 31 * result + (webPropertyRankReport != null ? webPropertyRankReport.hashCode() : 0);
        result = 31 * result + (timePeriodId != null ? timePeriodId.hashCode() : 0);
        return result;
    }
}
