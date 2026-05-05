package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Subtitle;
import aiss.dailymotionminer.model.videominer.VMCaption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "videominer.url=http://localhost:8080")
class CaptionMapperTest {

    @Test
    @DisplayName("Should map Subtitle to VMCaption when language is present")
    void toVMCaption_WithLanguage() {
        // Arrange
        Subtitle subtitle = new Subtitle();
        subtitle.setId("sub_001");
        subtitle.setLanguage("en");

        // Act
        VMCaption result = CaptionMapper.toVMCaption(subtitle);

        // Assert
        assertNotNull(result);
        assertEquals("sub_001", result.getId());
        assertEquals("en", result.getLanguage());
    }

    @Test
    @DisplayName("Should map Subtitle to VMCaption when language is null")
    void toVMCaption_WithoutLanguage() {
        // Arrange
        Subtitle subtitle = new Subtitle();
        subtitle.setId("sub_002");
        subtitle.setLanguage(null);

        // Act
        VMCaption result = CaptionMapper.toVMCaption(subtitle);

        // Assert
        assertNotNull(result);
        assertEquals("sub_002", result.getId());
        assertNull(result.getLanguage());
    }
}