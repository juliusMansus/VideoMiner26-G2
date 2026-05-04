package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelMapperTest {

    @Test
    @DisplayName("Should correctly map a real PeerTube Channel and Video list to a VMChannel")
    void toVMChannel() {
        Channel realPtChannel = new Channel();
        realPtChannel.setId(12345); // toVMChannel uses String.valueOf(), assuming this is a numeric type
        realPtChannel.setDisplayName("Open Source Education");
        realPtChannel.setDescription("A channel dedicated to free educational content.");
        realPtChannel.setCreatedAt("2023-10-01T12:00:00.000Z");

        List<VMVideo> realVideoList = new ArrayList<>();
        VMVideo video1 = new VMVideo();
        video1.setId("video-1");
        video1.setName("Intro to Spring Boot");
        realVideoList.add(video1);

        VMChannel result = ChannelMapper.toVMChannel(realPtChannel, realVideoList);

        assertNotNull(result, "The mapped VMChannel should not be null");

        assertEquals("12345", result.getId(), "ID should be mapped and converted to a String");
        assertEquals("Open Source Education", result.getName(), "Name should be mapped from displayName");
        assertEquals("A channel dedicated to free educational content.", result.getDescription(), "Description should match exactly");
        assertEquals("2023-10-01T12:00:00.000Z", result.getCreatedTime(), "Created time should match exactly");

        assertNotNull(result.getVideos(), "The videos list should not be null");
        assertEquals(1, result.getVideos().size(), "The videos list should contain 1 video");
        assertEquals(realVideoList, result.getVideos(), "The exact list of videos should be mapped");
    }
}