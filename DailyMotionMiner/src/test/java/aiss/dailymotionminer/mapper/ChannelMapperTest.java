package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.User;
import aiss.dailymotionminer.model.videominer.VMChannel;
import aiss.dailymotionminer.model.videominer.VMVideo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelMapperTest {

    @Test
    @DisplayName("Should successfully map a DailyMotion User and Video list to a VMChannel")
    void toVMChannel() {
        User dmUser = new User();
        dmUser.setScreenname("DailyMotion_Creator");
        dmUser.setDescription("An awesome channel dedicated to technology.");
        // DailyMotion typically uses Unix timestamps (Long) for dates
        dmUser.setCreatedTime(1714835520L);

        List<VMVideo> videos = new ArrayList<>();
        VMVideo video1 = new VMVideo();
        video1.setId("x12345");
        video1.setName("My Tech Video");
        videos.add(video1);

        VMChannel result = ChannelMapper.toVMChannel(dmUser, videos);

        assertNotNull(result, "The mapped VMChannel should not be null");

        assertNotNull(result.getId(), "The ID should be populated with a generated UUID");
        assertNotEquals("", result.getId(), "The UUID should not be empty");

        assertEquals("DailyMotion_Creator", result.getName(), "Name should be mapped from user screenname");
        assertEquals("An awesome channel dedicated to technology.", result.getDescription(), "Description should match exactly");
        assertEquals("1714835520", result.getCreatedTime(), "Created time should be converted to String");

        assertNotNull(result.getVideos(), "The videos list should not be null");
        assertEquals(1, result.getVideos().size(), "The videos list should contain 1 video");
        assertEquals(videos, result.getVideos(), "The exact list of videos should be mapped");
    }
}