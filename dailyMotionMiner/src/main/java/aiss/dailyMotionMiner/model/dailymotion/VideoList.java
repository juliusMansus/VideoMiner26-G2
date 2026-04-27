package aiss.dailyMotionMiner.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoList {
    @JsonProperty("list")
    private List<Video> list;

    public List<Video> getData() { return list; }

    public void setList(List<Video> list) {
        this.list = list;
    }
}
