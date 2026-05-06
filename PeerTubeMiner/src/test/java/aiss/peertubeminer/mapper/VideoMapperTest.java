package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.User; // Assumed standard model location
import aiss.peertubeminer.model.videominer.VMCaption;
import aiss.peertubeminer.model.videominer.VMComment;
import aiss.peertubeminer.model.videominer.VMVideo;
import jakarta.annotation.Nonnull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VideoMapperTest {

    @Test
    @DisplayName("Should map correctly when Video has an Account (True condition)")
    void toVMVideo_WithAccount() {
        Video realVideo = getVideo();

        List<VMComment> comments = new ArrayList<>();
        VMComment comment1 = new VMComment();
        comment1.setId("c1");
        comments.add(comment1);

        List<VMCaption> captions = new ArrayList<>();
        VMCaption caption1 = new VMCaption();
        caption1.setId("cap1");
        captions.add(caption1);

        VMVideo result = VideoMapper.toVMVideo(realVideo, comments, captions);

        assertNotNull(result, "The mapped VMVideo should not be null");
        assertEquals("550e8400-e29b-41d4-a716-446655440000", result.getId(), "UUID should map to ID");
        assertEquals("My Awesome Video", result.getName(), "Name should map correctly");
        assertEquals("A video about programming.", result.getDescription(), "Description should map correctly");
        assertEquals("2023-11-20T10:00:00.000Z", result.getReleaseTime(), "Release time should map correctly");

        assertNotNull(result.getAuthor(), "Author should not be null because an Account was provided");
        assertEquals("JohnDoe", result.getAuthor().getName(), "Author name should be mapped from User displayName");

        assertEquals(1, result.getComments().size(), "Comments list should be preserved");
        assertEquals(comments, result.getComments(), "Comments list should map directly");
        assertEquals(1, result.getCaptions().size(), "Captions list should be preserved");
        assertEquals(captions, result.getCaptions(), "Captions list should map directly");
    }

    @Nonnull
    private static Video getVideo() {
        User realAccount = new User();
        realAccount.setDisplayName("JohnDoe");
        realAccount.setUrl("https://peertube.local/users/johndoe");
        Video realVideo = new Video();
        realVideo.setUuid("550e8400-e29b-41d4-a716-446655440000");
        realVideo.setName("My Awesome Video");
        realVideo.setDescription("A video about programming.");
        realVideo.setPublishedAt("2023-11-20T10:00:00.000Z");
        realVideo.setAccount(realAccount);
        return realVideo;
    }

    @Test
    @DisplayName("Should map correctly when Video has no Account (False condition)")
    void toVMVideo_WithoutAccount() {
        // Arrange
        Video realVideo = new Video();
        realVideo.setUuid("11111111-2222-3333-4444-555555555555");
        realVideo.setName("Anonymous Video");
        realVideo.setDescription("Video with no author.");
        realVideo.setPublishedAt("2022-05-15T08:30:00.000Z");
        realVideo.setAccount(null); // Explicitly null

        List<VMComment> comments = new ArrayList<>();
        List<VMCaption> captions = new ArrayList<>();

        // Act
        VMVideo result = VideoMapper.toVMVideo(realVideo, comments, captions);

        // Assert
        assertNotNull(result, "The mapped VMVideo should not be null");
        assertEquals("11111111-2222-3333-4444-555555555555", result.getId(), "UUID should map to ID");
        assertEquals("Anonymous Video", result.getName(), "Name should map correctly");

        // Assert conditional behavior
        assertNull(result.getAuthor(), "Author should remain null when Account is null");

        // Assert Lists
        assertEquals(0, result.getComments().size(), "Comments list should be empty but assigned");
        assertEquals(0, result.getCaptions().size(), "Captions list should be empty but assigned");
    }
}