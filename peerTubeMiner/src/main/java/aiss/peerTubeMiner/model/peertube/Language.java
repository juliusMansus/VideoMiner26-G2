package aiss.peerTubeMiner.model.peertube;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Language {
    @JsonProperty("id")
    private String id;

    @JsonProperty("label")
    private String label;

    public String getId() { return id; }
    public String getLabel() { return label; }

    @Override
    public String toString() {
        return "Language{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
