package aiss.videominer.controller;

import aiss.videominer.Exception.CaptionNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CaptionRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Caption", description = "The caption management API")
@RestController
@RequestMapping("/videominer/captions")
public class CaptionController {

    @Autowired
    CaptionRepo captionRepo;
    @Autowired
    VideoController videoController;

    @Operation(
            summary = "Retrieve a captions list",
            description = "Get a list with all Caption objects there are.",
            tags = {"Caption", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of captions",
                    content = {@Content(
                            schema = @Schema(implementation = Caption.class),
                            mediaType = "application/json")})
    })
    @GetMapping
    public List<Caption> findAll() {
        return captionRepo.findAll();
    }

    @Operation(
            summary = "Retrieve a caption with the ID",
            description = "Get a Caption object by specifying its ID field.",
            tags = {"Caption", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Caption of the ID",
                    content = {@Content(
                            schema = @Schema(implementation = Caption.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Caption not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public Caption findOne(
            @Parameter(description = "ID of the caption to be searched")
            @PathVariable String id) throws CaptionNotFoundException {
        Optional<Caption> caption = captionRepo.findById(id);
        if (caption.isEmpty()) {
            throw new CaptionNotFoundException();
        }
        return caption.get();
    }

    @Operation(
            summary = "Retrieve captions from video",
            description = "Get a list of all Caption objects belonging to the video of the given ID.",
            tags = {"Caption", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of captions of the video",
                    content = {@Content(
                            schema = @Schema(implementation = Caption.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Video not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/video/{videoId}")
    public List<Caption> findByVideoId(
            @Parameter(description = "ID of the video to obtain captions from")
            @PathVariable String videoId) throws VideoNotFoundException {
        Video video = videoController.findOne(videoId);
        return video.getCaptions();
    }
}