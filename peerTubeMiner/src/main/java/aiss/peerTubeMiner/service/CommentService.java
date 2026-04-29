package aiss.peerTubeMiner.service;

import aiss.peerTubeMiner.mapper.CommentMapper;
import aiss.peerTubeMiner.model.peertube.Comment;
import aiss.peerTubeMiner.model.peertube.CommentList;
import aiss.peerTubeMiner.model.videominer.VMComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${peertube.baseurl}")
    private String baseUrl;

    public List<VMComment> getComments(String videoId, int maxComments) {
        CommentList commentList = restTemplate.getForObject(
                baseUrl + "/videos/"+videoId+ "/comment-threads?count=" + Math.min(maxComments, 100), CommentList.class);
        List<VMComment> comments = new ArrayList<>();
        if(commentList != null && commentList.getData() != null) {
            for (Comment comment : commentList.getData()) {
                if(!comment.isDeleted()) {
                    comments.add(CommentMapper.toVMComment(comment));
                }
            }
        }
        return comments;
    }
}
