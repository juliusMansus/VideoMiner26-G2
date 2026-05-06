package aiss.videominer.controller;

import aiss.videominer.Exception.CommentNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepo;
import aiss.videominer.repository.VideoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest {

    @Autowired
    private CommentController commentController;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private VideoRepo videoRepo;

    @BeforeEach
    void cleanDatabase() {
        // Clear repositories to ensure a clean state for every test
        commentRepo.deleteAll();
        videoRepo.deleteAll();
    }

    @Test
    @DisplayName("Retrieve all comments from the database")
    void findAll() {
        // Arrange: Seed two comments
        Comment c1 = new Comment();
        c1.setId("c1");
        c1.setText("First comment");

        Comment c2 = new Comment();
        c2.setId("c2");
        c2.setText("Second comment");

        commentRepo.save(c1);
        commentRepo.save(c2);

        // Act
        List<Comment> results = commentController.findAll();

        // Assert
        assertEquals(2, results.size(), "Should find exactly 2 comments");
    }

    @Test
    @DisplayName("Find a specific comment by ID")
    void findOne() throws CommentNotFoundException {
        // Arrange: Seed a comment
        String targetId = "target-comment-id";
        Comment comment = new Comment();
        comment.setId(targetId);
        comment.setText("Target text");
        commentRepo.save(comment);

        // Act
        Comment result = commentController.findOne(targetId);

        // Assert
        assertNotNull(result);
        assertEquals(targetId, result.getId());
        assertEquals("Target text", result.getText());
    }

    @Test
    @DisplayName("Throw CommentNotFoundException for non-existent ID")
    void findOne_NotFound() {
        // Act & Assert
        assertThrows(CommentNotFoundException.class, () -> {
            commentController.findOne("invalid-id");
        });
    }

    @Test
    @DisplayName("Find all comments belonging to a specific video")
    void findByVideoId() throws VideoNotFoundException {
        // Arrange: Create a video and associate comments with it
        Video video = new Video();
        video.setId("v-100");
        video.setName("Test Video");

        Comment c1 = new Comment();
        c1.setId("vc-1");
        c1.setText("Video comment 1");

        List<Comment> commentList = new ArrayList<>();
        commentList.add(c1);
        video.setComments(commentList);

        // Save to database
        videoRepo.save(video);
        commentRepo.save(c1);

        // Act
        List<Comment> results = commentController.findByVideoId("v-100");

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("vc-1", results.get(0).getId());
    }

    @Test
    @DisplayName("Throw VideoNotFoundException when searching comments for a missing video")
    void findByVideoId_NotFound() {
        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> {
            commentController.findByVideoId("ghost-video-id");
        });
    }
}