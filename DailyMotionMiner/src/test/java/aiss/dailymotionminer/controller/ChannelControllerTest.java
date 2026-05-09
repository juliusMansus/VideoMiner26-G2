package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.exception.ChannelNotFoundException;
import aiss.dailymotionminer.model.videominer.VMChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelControllerTest {

    @Autowired
    ChannelController channelController;

    private final String validChannelId = "x50q15c";



    @Test
    @DisplayName("getChannel should return a VMChannel when given a valid DailyMotion ID")
    void getChannel_Success() throws ChannelNotFoundException {
        VMChannel result = channelController.getChannel(validChannelId, 10, 2);
        assertNotNull(result, "The returned channel should not be null");
        assertEquals(validChannelId, result.getId(), "The returned channel ID should match the request");
    }

    @Test
    @DisplayName("fetchAndSendChannel should return VMChannel on valid ID and successful POST")
    void fetchAndSendChannel_Success() throws ChannelNotFoundException {
        VMChannel result = channelController.fetchAndSendChannel(validChannelId, 10, 2);
        assertNotNull(result, "The channel should be returned after being sent to VideoMiner");
    }

}