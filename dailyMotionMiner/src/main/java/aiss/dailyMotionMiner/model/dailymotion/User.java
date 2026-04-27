package aiss.dailyMotionMiner.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("id")
    private String id;

    @JsonProperty("screenname")
    private String screenname;

    @JsonProperty("url")
    private String url;

    @JsonProperty("avatar_25_url")
    private String avatar25Url;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_time")
    private Long createdTime;


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getScreenname() { return screenname; }

    public void setScreenname(String screenname) { this.screenname = screenname; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getAvatar25Url() { return avatar25Url; }

    public void setAvatar25Url(String avatar25Url) { this.avatar25Url = avatar25Url; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
