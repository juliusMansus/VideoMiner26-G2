package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VMCaption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// We inject the real DailyMotion API base URL so the RestTemplate can reach the actual internet endpoints
@SpringBootTest(properties = "dailymotion.baseurl=https://api.dailymotion.com")
class CaptionServiceTest {

    @Autowired
    private CaptionService captionService;

    @Test
    @DisplayName("Should fetch and map subtitles successfully for a valid Video ID")
    void getCaptions_ValidVideo() {
        String validVideoId = "x84ur12";

        List<VMCaption> captions = captionService.getCaptions(validVideoId);

        assertNotNull(captions, "The returned captions list should never be null, even if empty");
    }

    @Test
    @DisplayName("Should throw an exception when making a real API call with an invalid Video ID")
    void getCaptions_InvalidVideo() {
        String invalidVideoId = "this_is_a_fake_video_id_9999";

        assertThrows(HttpClientErrorException.class, () -> {
            captionService.getCaptions(invalidVideoId);
        }, "RestTemplate should throw an HTTP error when the DailyMotion API rejects the ID");
    }
}