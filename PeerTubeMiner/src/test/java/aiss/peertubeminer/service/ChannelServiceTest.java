package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMChannel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;
    @Test
    @DisplayName("Get channel")
    void getChannel() {
        VMChannel channel = channelService.getChannel("blender_open_movies@video.blender.org", 2, 2);
        System.out.println(channel);
    }
}