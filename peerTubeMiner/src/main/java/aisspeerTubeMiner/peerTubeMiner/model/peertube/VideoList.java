package aisspeerTubeMiner.peerTubeMiner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoList {
    @JsonProperty("data")
    private List<Video> data;

    public List<Video> getData() { return data; }
}
