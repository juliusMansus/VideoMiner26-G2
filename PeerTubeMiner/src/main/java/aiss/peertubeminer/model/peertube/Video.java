package aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishedAt")
    private String publishedAt;

    @JsonProperty("account")
    private User account;

    public String getUuid() { return uuid; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPublishedAt() { return publishedAt; }
    public User getAccount() { return account; }

    @Override
    public String toString() {
        return "Video{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", account=" + account +
                '}';
    }
}
