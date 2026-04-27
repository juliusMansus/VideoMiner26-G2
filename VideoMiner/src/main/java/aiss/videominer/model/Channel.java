package aiss.videominer.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotEmpty(message = "Channel name cannot be empty")
    private String name;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @Column(name="createdTime")
    @NotEmpty(message = "Channel creation time cannot be empty")
    private String createdTime;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "channelId")
    @NotNull(message = "Channel videos cannot be null")
    private List<Video> videos;
    public Channel(){}


    public Channel(String name, String description, String createdTime, List<Video> videos){
        this.name=name;
        this.description=description;
        this.createdTime=createdTime;
        this.videos=new ArrayList<>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", videos=" + videos +
                '}';
    }
}
