package aisspeerTubeMiner.peerTubeMiner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdAt")
    private String createdAt;

    //SOME COMMENTS ARE DELETED , so I added this property to skip them
    @JsonProperty("isDeleted")
    private boolean isDeleted;

    public Integer getId() { return id; }
    public String getText() { return text; }
    public String getCreatedAt() { return createdAt; }
    public boolean isDeleted() { return isDeleted; }
}
