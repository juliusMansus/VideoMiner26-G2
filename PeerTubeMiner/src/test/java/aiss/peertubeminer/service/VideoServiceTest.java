package aiss.peertubeminer.service;

import aiss.peertubeminer.model.videominer.VMVideo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VideoServiceTest {

    @Autowired
    VideoService videoService;

    @Test
    @DisplayName("Get Videos of a channel")
    void getVideos() {
        List<VMVideo> videos = videoService.getVideos("blender_open_movies@video.blender.org",
                2, 2);
        System.out.println(videos);
    }
}