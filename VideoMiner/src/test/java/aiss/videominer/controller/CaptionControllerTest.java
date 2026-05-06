package aiss.videominer.controller;

import aiss.videominer.Exception.CaptionNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepo;
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
class CaptionControllerTest {

    @Autowired
    private CaptionController captionController;

    @Autowired
    private CaptionRepo captionRepo;

    @Autowired
    private VideoRepo videoRepo;

    @BeforeEach
    void cleanDatabase() {
        // Ensure a clean state by clearing repositories before each test
        captionRepo.deleteAll();
        videoRepo.deleteAll();
    }

    @Test
    @DisplayName("Retrieve all captions from the database")
    void findAll() {
        // Arrange: Seed two captions
        Caption c1 = new Caption();
        c1.setId("cap-1");
        c1.setName("English Subtitles");

        Caption c2 = new Caption();
        c2.setId("cap-2");
        c2.setName("Spanish Subtitles");

        captionRepo.save(c1);
        captionRepo.save(c2);

        // Act
        List<List<Caption>> results = List.of(captionController.findAll());

        // Assert
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).size(), "Should find exactly 2 captions in the database");
    }

    @Test
    @DisplayName("Find a specific caption by its ID")
    void findOne() throws CaptionNotFoundException {
        // Arrange: Seed a specific caption
        String targetId = "find-me";
        Caption caption = new Caption();
        caption.setId(targetId);
        caption.setName("Target Caption");
        captionRepo.save(caption);

        // Act
        Caption result = captionController.findOne(targetId);

        // Assert
        assertNotNull(result);
        assertEquals(targetId, result.getId());
        assertEquals("Target Caption", result.getName());
    }

    @Test
    @DisplayName("Throw CaptionNotFoundException when the ID does not exist")
    void findOne_NotFound() {
        // Act & Assert
        assertThrows(CaptionNotFoundException.class, () -> {
            captionController.findOne("non-existent-caption");
        }, "Should throw CaptionNotFoundException for a missing ID");
    }

    @Test
    @DisplayName("Find all captions for a specific video")
    void findByVideoId() throws VideoNotFoundException {
        // Arrange: Create a video and associate captions with it
        Video video = new Video();
        video.setId("vid-99");
        video.setName("DailyMotion Test Video");

        Caption c1 = new Caption();
        c1.setId("v-cap-1");
        c1.setName("CC 1");

        List<Caption> captionList = new ArrayList<>();
        captionList.add(c1);
        video.setCaptions(captionList);

        // Save both to persistence
        videoRepo.save(video);
        captionRepo.save(c1);

        // Act
        List<Caption> results = captionController.findByVideoId("vid-99");

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("v-cap-1", results.get(0).getId());
    }

    @Test
    @DisplayName("Throw VideoNotFoundException when the video ID does not exist")
    void findByVideoId_NotFound() {
        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> {
            captionController.findByVideoId("missing-video-123");
        }, "Should throw VideoNotFoundException when searching for captions of a non-existent video");
    }
}