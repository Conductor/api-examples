package beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anihalani on 6/8/15.
 *
 * Category Class to map JSON data returned from Category endpoint
 */

@JsonPropertyOrder({"name", "trackedSearchIds", "created", "modified" })
public class Category {

    private String name;
    private List<Integer> trackedSearchIds = new ArrayList<>();
    private String created;
    private String modified;

    private int categoryId;

    /**
     * @return The Category id
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId - The Category Id to be set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }



    /**
     * @return The category name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name - The category name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The list of Tracked Search ids
     */
    @JsonProperty("trackedSearchIds")
    public List<Integer> getTrackedSearchIds() {
        return trackedSearchIds;
    }

    /**
     * @param trackedSearchIds - The list of Tracked Search ids
     */
    public void setTrackedSearchIds(List<Integer> trackedSearchIds) {
        this.trackedSearchIds = trackedSearchIds;
    }

    /**
     * @return The date when the category was created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     * @param created - The date when the category was created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     * @return The date when the category was modified
     */
    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    /**
     * @param modified The date when the category was modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }
}

