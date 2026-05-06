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

    @Test
    @DisplayName("getChannel should return a VMChannel when given a valid DailyMotion ID")
    void getChannel_Success() throws ChannelNotFoundException {
        String validChannelId = "x6n9ve";

        VMChannel result = channelController.getChannel(validChannelId, 10, 2);

        assertNotNull(result, "The returned channel should not be null");
        assertEquals(validChannelId, result.getId(), "The returned channel ID should match the request");
    }

    @Test
    @DisplayName("getChannel should throw ChannelNotFoundException when the ID is invalid")
    void getChannel_NotFound() {
        String invalidId = "THIS_ID_IS_TOTALLY_INVALID_12345";
        assertThrows(ChannelNotFoundException.class, () -> {
            channelController.getChannel(invalidId, 10, 2);
        }, "An invalid ID should trigger a ChannelNotFoundException");
    }

    @Test
    @DisplayName("fetchAndSendChannel should return VMChannel on valid ID and successful POST")
    void fetchAndSendChannel_Success() throws ChannelNotFoundException {
        String validChannelId = "x6n9ve";
        VMChannel result = channelController.fetchAndSendChannel(validChannelId, 10, 2);
        assertNotNull(result, "The channel should be returned after being sent to VideoMiner");
    }

    @Test
    @DisplayName("fetchAndSendChannel should throw ChannelNotFoundException on invalid ID")
    void fetchAndSendChannel_NotFound() {
        String invalidId = "NON_EXISTENT_ID_999";
        assertThrows(ChannelNotFoundException.class, () -> {
            channelController.fetchAndSendChannel(invalidId, 10, 2);
        }, "An invalid ID should trigger a ChannelNotFoundException during the fetch-and-send process");
    }
}