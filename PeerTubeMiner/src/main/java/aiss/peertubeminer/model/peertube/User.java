package aiss.peertubeminer.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("url")
    private String url;

    @JsonProperty("avatars")
    private List<Avatar> avatars;

    public Integer getId() { return id; }
    public String getDisplayName() { return displayName; }
    public String getUrl() { return url; }
    public List<Avatar> getAvatars() { return avatars; }
}
