package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VMChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DailyMotionServiceTest {

    @Autowired
    private DailyMotionService dailyMotionService;

    private final String validChannelId = "x1v68m6";

    @Test
    @DisplayName("Test getChannel with real service calls")
    void getChannel() {
        int maxVideos = 2;
        int maxPages = 1;

        VMChannel channel = dailyMotionService.getChannel(validChannelId, maxVideos, maxPages);

        assertNotNull(channel, "The returned channel should not be null");
        assertNotNull(channel.getName(), "The channel name should be populated");
        assertNotNull(channel.getVideos(), "The videos list should not be null");
        assertTrue(channel.getVideos().size() <= maxVideos, "Should respect the maxVideos limit");
    }

    @Test
    @DisplayName("Test getChannelAndSendToVideoMiner with real REST calls")
    void getChannelAndSendToVideoMiner() {
        int maxVideos = 1;
        int maxPages = 1;
        try {
            VMChannel channel = dailyMotionService.getChannelAndSendToVideoMiner(validChannelId, maxVideos, maxPages);
            assertNotNull(channel, "The returned channel should not be null");
            assertEquals(validChannelId, channel.getId(), "The ID should match the requested channel");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Connection refused") || e instanceof HttpClientErrorException,
                    "Expected connection error to VideoMiner, but got: " + e.getClass().getSimpleName());
        }
    }

    @Test
    @DisplayName("Test getChannel with invalid ID throws exception")
    void getChannelWithInvalidId() {
        assertThrows(Exception.class, () -> {
            dailyMotionService.getChannel("non_existent_user_12345", 1, 1);
        }, "Should throw an exception when the channel ID is invalid");
    }
}