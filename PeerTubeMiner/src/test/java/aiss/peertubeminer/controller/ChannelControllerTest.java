package aiss.peertubeminer.controller;

import aiss.peertubeminer.exception.ChannelNotFoundException;
import aiss.peertubeminer.model.videominer.VMChannel;
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
    @DisplayName("getChannel should return VMChannel on valid real ID")
    void getChannel() throws ChannelNotFoundException {
        String realChannelId = "1";
        VMChannel result = channelController.getChannel(realChannelId, 10, 2);
        assertNotNull(result, "Channel should not be null");
    }

    @Test
    @DisplayName("sendToVideoMiner should fetch and POST channel successfully")
    void sendToVideoMiner() throws ChannelNotFoundException {
        String realChannelId = "1";
        VMChannel result = channelController.sendToVideoMiner(realChannelId, 10, 2);
        assertNotNull(result, "Channel should not be null after POSTing");
    }
}