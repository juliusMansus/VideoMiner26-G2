package aisspeerTubeMiner.peerTubeMiner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Avatar {
    @JsonProperty("fileUrl")
    private String fileUrl;

    public String getFileUrl() { return fileUrl; }
}
