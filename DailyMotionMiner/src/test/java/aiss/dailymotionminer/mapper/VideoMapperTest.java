package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.User;
import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMCaption;
import aiss.dailymotionminer.model.videominer.VMVideo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoMapperTest {

    @Test
    @DisplayName("Should map Video to VMVideo with owner and tags (True conditions)")
    void toVMVideo_FullData() {
        Video video = new Video();
        video.setId("dm_123");
        video.setTitle("DM Video");
        video.setCreatedTime(1714835520L); // Sample timestamp

        video.setOwnerScreenname("DailyUser");

        List<String> tags = new ArrayList<>();
        tags.add("tutorial");
        video.setTags(tags);

        VMVideo result = VideoMapper.toVMVideo(video, new ArrayList<>());

        assertEquals("dm_123", result.getId());
        assertNotNull(result.getAuthor());
        assertFalse(result.getComments().isEmpty()); // Tags are converted to comments
    }

    @Test
    @DisplayName("Should map Video safely when owner and tags are null (False conditions)")
    void toVMVideo_NullData() {
        Video video = new Video();
        video.setId("dm_456");
        video.setCreatedTime(0L);
        video.setOwnerId(null);
        video.setTags(null);

        VMVideo result = VideoMapper.toVMVideo(video, new ArrayList<>());

        assertNull(result.getAuthor());
        assertTrue(result.getComments().isEmpty());
    }
}