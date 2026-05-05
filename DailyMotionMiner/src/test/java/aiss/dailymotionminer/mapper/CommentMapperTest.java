package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class CommentMapperTest {

    @Test
    @DisplayName("Should successfully map a DailyMotion Video tag to a VMComment")
    void toVMComment() {
        // Arrange
        // 1. Create a real DailyMotion Video object to act as the source
        Video dmVideo = new Video();
        // Assuming createdTime is a Long (Unix timestamp) typical of the DailyMotion API
        dmVideo.setCreatedTime(1714835520L);

        // 2. In DailyMotion, tags are mapped to comments, so we provide a sample tag
        String tag = "spring-boot-tutorial";

        // Act
        // Map the tag and video using the static mapper method
        VMComment result = CommentMapper.toVMComment(tag, dmVideo);

        // Assert
        assertNotNull(result, "The mapped VMComment should not be null");

        // Verify that the ID was generated randomly as a UUID
        assertNotNull(result.getId(), "The ID should be populated with a generated UUID");
        assertNotEquals("", result.getId(), "The UUID should not be empty");

        // Verify the text mapped correctly from the tag
        assertEquals("spring-boot-tutorial", result.getText(), "Text should match the provided tag exactly");

        // Verify the creation time was extracted from the video and converted properly
        assertEquals("1714835520", result.getCreatedOn(), "CreatedOn should map from the video's createdTime and be a String");
    }
}