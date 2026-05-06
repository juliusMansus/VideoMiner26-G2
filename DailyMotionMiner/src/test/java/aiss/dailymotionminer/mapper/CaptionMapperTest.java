package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Subtitle;
import aiss.dailymotionminer.model.videominer.VMCaption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CaptionMapperTest {

    @Test
    @DisplayName("Should map Subtitle to VMCaption when language is present")
    void toVMCaption_WithLanguage() {
        Subtitle subtitle = new Subtitle();
        subtitle.setId("sub_001");
        subtitle.setLanguage("en");

        VMCaption result = CaptionMapper.toVMCaption(subtitle);

        assertNotNull(result);
        assertEquals("sub_001", result.getId());
        assertEquals("en", result.getLanguage());
    }

    @Test
    @DisplayName("Should map Subtitle to VMCaption when language is null")
    void toVMCaption_WithoutLanguage() {
        Subtitle subtitle = new Subtitle();
        subtitle.setId("sub_002");
        subtitle.setLanguage(null);

        VMCaption result = CaptionMapper.toVMCaption(subtitle);

        assertNotNull(result);
        assertEquals("sub_002", result.getId());
        assertNull(result.getLanguage());
    }
}