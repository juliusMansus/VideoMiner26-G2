package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {

    @Test
    @DisplayName("Should successfully map a DailyMotion Video tag to a VMComment")
    void toVMComment() {
        Video dmVideo = new Video();
        // Assuming createdTime is a Long (Unix timestamp) typical of the DailyMotion API
        dmVideo.setCreatedTime(1714835520L);
        String tag = "spring-boot-tutorial";
        VMComment result = CommentMapper.toVMComment(tag, dmVideo);

        assertNotNull(result, "The mapped VMComment should not be null");

        assertNotNull(result.getId(), "The ID should be populated with a generated UUID");
        assertNotEquals("", result.getId(), "The UUID should not be empty");

        assertEquals("spring-boot-tutorial", result.getText(), "Text should match the provided tag exactly");
        assertEquals("1714835520", result.getCreatedOn(), "CreatedOn should map from the video's createdTime and be a String");
    }
}