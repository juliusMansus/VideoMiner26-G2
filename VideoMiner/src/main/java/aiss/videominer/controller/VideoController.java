package aiss.videominer.controller;

import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    VideoRepo videoRepo;

    @GetMapping
    public List<Video> findAll() { return videoRepo.findAll(); }

    @GetMapping("/{id}")
    public Video findOneById(@PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = videoRepo.findById(id);
        if (video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get();
    }

}
