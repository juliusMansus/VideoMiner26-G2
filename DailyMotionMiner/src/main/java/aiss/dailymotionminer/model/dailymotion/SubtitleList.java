package aiss.dailymotionminer.model.dailymotion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubtitleList {
    @JsonProperty("list")
    private List<Subtitle> list;

    public List<Subtitle> getData() { return list; }

    public void setList(List<Subtitle> list) {
        this.list = list;
    }
}
