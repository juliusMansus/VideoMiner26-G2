package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMCaption;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class CaptionServiceTest {

    @Autowired
    CaptionService captionService;

    @Test
    @DisplayName("Get Captions of a video")
    void getCaptions() {
        List<VMCaption> captions = captionService.getCaptions("udhki4GbH2NtTx6aGJB6Mr");
        assertFalse(captions.isEmpty(), "The Captions List is empty");
        System.out.println(captions);

    }
}