package aiss.videominer.controller;

import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class VideoControllerTest {

    @Autowired
    private VideoController videoController;

    @Autowired
    private VideoRepo videoRepo;

    private Video testVideo;

    @BeforeEach
    void setUp() {
        // We clear the repo and seed a real video to ensure consistent results
        videoRepo.deleteAll();

        testVideo = new Video();
        testVideo.setId("v12345");
        testVideo.setName("DailyMotion Integration Test Video");
        testVideo.setDescription("Testing the VideoMiner storage logic.");
        testVideo.setReleaseTime("2024-05-06");

        videoRepo.save(testVideo);
    }

    @Test
    @DisplayName("Retrieve all videos from the database")
    void findAll() {
        // Act
        List<Video> videos = videoController.findAll();

        // Assert
        assertNotNull(videos, "The video list should not be null");
        assertFalse(videos.isEmpty(), "The video list should contain the seeded test video");
        assertEquals(1, videos.size(), "There should be exactly one video in the repository");
        assertEquals("v12345", videos.get(0).getId());
    }

    @Test
    @DisplayName("Find a specific video by its unique ID")
    void findOneById() throws VideoNotFoundException {
        // Act
        Video result = videoController.findOneById("v12345");

        // Assert
        assertNotNull(result, "The video should be found");
        assertEquals("v12345", result.getId());
        assertEquals("DailyMotion Integration Test Video", result.getName());
    }

    @Test
    @DisplayName("Throw VideoNotFoundException when the ID does not exist")
    void findOneById_NotFound() {
        // Arrange
        String fakeId = "non_existent_id_999";

        // Act & Assert
        assertThrows(VideoNotFoundException.class, () -> {
            videoController.findOneById(fakeId);
        }, "Should throw VideoNotFoundException for an ID not present in the database");
    }
}