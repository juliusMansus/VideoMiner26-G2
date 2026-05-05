package aiss.videominer.controller;

import aiss.videominer.Exception.CaptionNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepo;
import aiss.videominer.repository.VideoRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Caption", description = "API para gestionar los subtítulos")
@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    @Autowired
    CaptionRepo captionRepo;

    @Autowired
    VideoRepo videoRepo;

    @Operation(summary = "Listar todos los subtítulos")
    @GetMapping
    public List<Caption> findAll() {
        return captionRepo.findAll();
    }

    @Operation(summary = "Obtener un subtítulo por ID")
    @GetMapping("/{id}")
    public Caption findOne(@PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepo.findById(id);
        if (!caption.isPresent()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    @Operation(summary = "Obtener todos los subtítulos de un vídeo específico")
    @GetMapping("/video/{videoId}")
    public List<Caption> findByVideoId(@PathVariable String videoId) throws VideoNotFoundException {
        Optional<Video> video = videoRepo.findById(videoId);
        if (!video.isPresent()) {
            throw new VideoNotFoundException();
        }
        return video.get().getCaptions();
    }
}