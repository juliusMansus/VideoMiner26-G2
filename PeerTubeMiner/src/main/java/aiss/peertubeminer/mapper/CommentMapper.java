package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.videominer.VMComment;

public class CommentMapper {
    public static VMComment toVMComment(Comment ptComment) {
        VMComment comment = new VMComment();
        comment.setId(String.valueOf(ptComment.getId()));
        comment.setText(ptComment.getText());
        comment.setCreatedOn(ptComment.getCreatedAt());
        return comment;
    }
}
