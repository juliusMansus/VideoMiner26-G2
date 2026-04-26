package aiss.peerTubeMiner.service;

import aiss.peerTubeMiner.model.videominer.VMComment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    void getComments() {
        List<VMComment> comments = commentService.getComments("3d95fb3d-c866-42c8-9db1-fe82f48ccb95", 4);
        assertFalse(comments.isEmpty(), "The Comment List is empty");
        System.out.println(comments);
    }
}