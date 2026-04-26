package aiss.peerTubeMiner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Caption {

    //I USED captionPath as ID since caption has no ID in the response
    @JsonProperty("captionPath")
    private String captionPath;

    @JsonProperty("language")
    private Language language;

    public String getCaptionPath() {
        return captionPath;
    }

    public Language getLanguage() {
        return language;
    }

    public void setCaptionPath(String captionPath) {
        this.captionPath = captionPath;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
