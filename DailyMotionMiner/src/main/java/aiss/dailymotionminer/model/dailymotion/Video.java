package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_time")
    private Long createdTime;

    @JsonProperty("owner.id")
    private String ownerId;

    @JsonProperty("owner.screenname")
    private String ownerScreenname;

    @JsonProperty("owner.url")
    private String ownerUrl;

    @JsonProperty("owner.avatar_25_url")
    private String ownerAvatar;

    @JsonProperty("tags")
    private List<String> tags;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setOwnerScreenname(String ownerScreenname) {
        this.ownerScreenname = ownerScreenname;
    }

    public String getOwnerScreenname() {
        return ownerScreenname;
    }

    public String getOwnerUrl() {
        return ownerUrl;
    }

    public void setOwnerUrl(String ownerUrl) {
        this.ownerUrl = ownerUrl;
    }

    public String getOwnerAvatar() {
        return ownerAvatar;
    }

    public void setOwnerAvatar(String ownerAvatar) {
        this.ownerAvatar = ownerAvatar;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) { this.tags = tags; }


    public User getOwner() {
        if (ownerId == null && ownerScreenname == null) { return null; }
        User user = new User();
        user.setId(this.ownerId);
        user.setScreenname(this.ownerScreenname);
        user.setUrl(this.ownerUrl);
        user.setAvatar25Url(this.ownerAvatar);
        return user;
    }

}

