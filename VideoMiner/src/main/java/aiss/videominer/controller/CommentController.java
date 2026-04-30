package aiss.videominer.controller;

import aiss.videominer.Exception.CommentNotFoundException;
import aiss.videominer.Exception.VideoNotFoundException;
import aiss.videominer.model.Comment;
import aiss.videominer.model.Video;
import aiss.videominer.repository.CommentRepo;
import aiss.videominer.repository.VideoRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Comment", description = "API para gestionar los comentarios")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    VideoRepo videoRepo;

    @GetMapping
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Comment findOne(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepo.findById(id);
        if (comment.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

    @GetMapping("/video/{videoId}")
    public List<Comment> findByVideoId(@PathVariable String videoId) throws VideoNotFoundException {
        Optional<Video> video = videoRepo.findById(videoId);
        if (video.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return video.get().getComments();
    }
}