package aiss.videominer.controller;

import aiss.videominer.Exception.CommentNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Caption;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepo;
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

@Tag(name = "Comment", description = "The comment management API")
@RestController
@RequestMapping("/videominer/comments")
public class CommentController {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    VideoController videoController;

    @Operation(
            summary = "Retrieve a comments list",
            description = "Get a list with all Comment objects there are.",
            tags = {"Comment", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of comments",
                    content = {@Content(
                            schema = @Schema(implementation = Comment.class),
                            mediaType = "application/json")})
    })
    @GetMapping
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Operation(
            summary = "Retrieve a comment with the ID",
            description = "Get a Comment object by specifying its ID field.",
            tags = {"Comment", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comment of the ID",
                    content = {@Content(
                            schema = @Schema(implementation = Comment.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Comment not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public Comment findOne(
            @Parameter(description = "ID of the comment to be searched")
            @PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepo.findById(id);
        if (comment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    @Operation(
            summary = "Retrieve comments from video",
            description = "Get a list of all Comment objects belonging to the video of the given ID.",
            tags = {"Comment", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of comments of the video",
                    content = {@Content(
                            schema = @Schema(implementation = Comment.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Video not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/video/{videoId}")
    public List<Comment> findByVideoId(
            @Parameter(description = "ID of the video to obtain comments from")
            @PathVariable String videoId) throws VideoNotFoundException {
        Video video = videoController.findOne(videoId);
        return video.getComments();
    }
}