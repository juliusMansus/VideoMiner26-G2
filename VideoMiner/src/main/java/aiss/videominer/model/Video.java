package aiss.videominer.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "Video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotEmpty(message = "Video name cannot be empty")
    private String name;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @Column(name="releaseTime")
    @NotEmpty(message = "Video release time cannot be empty")
    private String releaseTime;

    @OneToOne(cascade = CascadeType.ALL)
    private User author;

    @Column(name="comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    private List<Comment> comments;

    @Column(name="captions")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "videoId")
    private List<Caption> captions;

    public Video(String name, String description, String releaseTime, User author, List<Comment> comments, List<Caption> captions){
        this.name=name;
        this.description=description;
        this.releaseTime=releaseTime;
        this.author = author != null ? author : new User();
        this.comments = comments != null ? comments : new ArrayList<>(); 
        this.captions = captions != null ? captions : new ArrayList<>();
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", author=" + author +
                ", comments=" + comments +
                ", captions=" + captions +
                '}';
    }
}
