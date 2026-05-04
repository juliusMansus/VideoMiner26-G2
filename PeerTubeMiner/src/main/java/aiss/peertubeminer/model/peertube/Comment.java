package aiss.peertubeminer.model.peertube;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
