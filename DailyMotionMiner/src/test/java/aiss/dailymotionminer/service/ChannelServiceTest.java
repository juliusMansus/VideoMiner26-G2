package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VMChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

// Inject the real DailyMotion API URL so the RestTemplate can resolve the endpoint
@SpringBootTest(properties = "dailymotion.baseurl=https://api.dailymotion.com")
class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @Test
    @DisplayName("Should successfully fetch, map, and return a VMChannel for a valid DailyMotion ID")
    void getChannel_ValidId() {
        // "dailymotion" is an official, highly active channel.
        String validId = "dailymotion";
        int maxVideos = 2;
        int maxPages = 1;

        VMChannel result = channelService.getChannel(validId, maxVideos, maxPages);

        assertNotNull(result, "The resulting VMChannel should not be null");

        assertNotNull(result.getId(), "The ID should be populated with a newly generated UUID");
        assertNotEquals(validId, result.getId(), "The ID should be a UUID, not the original DailyMotion ID");

        assertNotNull(result.getName(), "The channel name should have been fetched from the API");
        assertNotNull(result.getCreatedTime(), "The created time should have been fetched and parsed");

        assertNotNull(result.getVideos(), "The videos list should not be null");
    }

    @Test
    @DisplayName("Should throw an HTTP exception when given an invalid DailyMotion channel ID")
    void getChannel_InvalidId() {
        String invalidId = "fake_channel_id_999999";

        assertThrows(HttpClientErrorException.class, () -> {
            channelService.getChannel(invalidId, 1, 1);
        }, "RestTemplate should throw an HTTP error when the DailyMotion API rejects the channel ID");
    }
}