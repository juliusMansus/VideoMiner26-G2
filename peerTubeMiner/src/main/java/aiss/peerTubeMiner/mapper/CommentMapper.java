package aiss.peerTubeMiner.mapper;

import aiss.peerTubeMiner.model.peertube.Comment;
import aiss.peerTubeMiner.model.videominer.VMComment;

public class CommentMapper {
    public static VMComment toVMComment(Comment ptComment) {
        VMComment comment = new VMComment();
        comment.setId(String.valueOf(ptComment.getId()));
        comment.setText(ptComment.getText());
        comment.setCreatedOn(ptComment.getCreatedAt());
        return comment;
    }
}
