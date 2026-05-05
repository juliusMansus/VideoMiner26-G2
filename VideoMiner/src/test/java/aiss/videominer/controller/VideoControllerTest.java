package aiss.videominer.controller;

import aiss.videominer.model.Video;
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

    @Test
    @DisplayName("Get all videos")
    void findAll() {
        List<Video> videos = videoController.findAll();
        assertNotNull(videos);
        assertFalse(videos.isEmpty(), "Videos list is empty");
        System.out.println(videos);
    }

    @Test
    @DisplayName("Find one video by id")
    void findOneById() {
        // TODO
    }
}