package aiss.videominer.controller;

import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Tag(name = "Video", description = "The video management API")
@RestController
@RequestMapping("/videominer/videos")
public class VideoController {

    @Autowired
    VideoRepo videoRepo;

    @Operation(
            summary = "Retrieve a videos list",
            description = "Get a list with all Video objects there are.",
            tags = {"Video", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of videos",
                    content = {@Content(
                            schema = @Schema(implementation = Video.class),
                            mediaType = "application/json")})
    })
    @GetMapping
    public List<Video> findAll() { return videoRepo.findAll(); }

    @Operation(
            summary = "Retrieve a video with the ID",
            description = "Get a Video object by specifying its ID field.",
            tags = {"Video", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Video of the ID",
                    content = {@Content(
                            schema = @Schema(implementation = Video.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Video not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public Video findOne(
            @Parameter(description = "ID of channel to be searched.")
            @PathVariable String id) throws VideoNotFoundException {
        Optional<Video> video = videoRepo.findById(id);
        if (video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get();
    }

}
