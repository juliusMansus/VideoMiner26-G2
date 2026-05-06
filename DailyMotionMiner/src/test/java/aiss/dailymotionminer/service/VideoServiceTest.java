package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VMVideo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;
    final String validUserId = "x2ns7z1";

    @Test
    @DisplayName("Should successfully fetch, map, and return a list of VMVideos for a valid DailyMotion User ID")
    void getVideos_ValidId() {

        int maxVideos = 2;
        int maxPages = 1;

        List<VMVideo> result = videoService.getVideos(validUserId, maxVideos, maxPages);

        assertNotNull(result, "The resulting video list should not be null");
        assertFalse(result.isEmpty(), "The video list should not be empty for an active channel");
        assertTrue(result.size() <= maxVideos, "The number of fetched videos should respect the maxVideos limit");

        VMVideo firstVideo = result.get(0);
        assertNotNull(firstVideo.getId(), "The video ID should be populated from the API");
        assertNotNull(firstVideo.getName(), "The video title should be populated from the API");
        assertNotNull(firstVideo.getReleaseTime(), "The created time should have been fetched and parsed");

        assertNotNull(firstVideo.getCaptions(), "The captions list should be instantiated");
        assertNotNull(firstVideo.getComments(), "The comments list (tags) should be instantiated");
    }

}