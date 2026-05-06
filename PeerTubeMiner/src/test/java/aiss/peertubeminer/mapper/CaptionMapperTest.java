package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Caption;
import aiss.peertubeminer.model.peertube.Language; // Assumed standard model location
import aiss.peertubeminer.model.videominer.VMCaption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CaptionMapperTest {

    @Test
    @DisplayName("Should map correctly when Language is present")
    void toVMCaption_WithLanguage() {

        Language realLanguage = new Language();
        realLanguage.setId("en");

        Caption realCaption = new Caption();
        realCaption.setCaptionPath("/captions/english.vtt");
        realCaption.setLanguage(realLanguage);

        VMCaption result = CaptionMapper.toVMCaption(realCaption);

        assertNotNull(result, "VMCaption should not be null");
        assertEquals("/captions/english.vtt", result.getId(), "ID should match the caption path");
        assertEquals("/captions/english.vtt", result.getName(), "Name should match the caption path");
        assertEquals("en", result.getLanguage(), "Language ID should be mapped correctly");
    }

    @Test
    @DisplayName("Should map correctly when Language is null")
    void toVMCaption_WithoutLanguage() {
        Caption realCaption = new Caption();
        realCaption.setCaptionPath("/captions/unknown.vtt");
        realCaption.setLanguage(null);

        VMCaption result = CaptionMapper.toVMCaption(realCaption);

        assertNotNull(result, "VMCaption should not be null");
        assertEquals("/captions/unknown.vtt", result.getId(), "ID should match the caption path");
        assertEquals("/captions/unknown.vtt", result.getName(), "Name should match the caption path");
        assertNull(result.getLanguage(), "Language should remain null in VMCaption");
    }
}