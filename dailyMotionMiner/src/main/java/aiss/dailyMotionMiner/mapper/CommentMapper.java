package aiss.dailyMotionMiner.mapper;

import aiss.dailyMotionMiner.model.videominer.VMComment;

import java.util.UUID;

public class CommentMapper {
    public static VMComment toVMComment(String tag){
        VMComment newComment=new VMComment();
        newComment.setId(UUID.randomUUID().toString());
        newComment.setText(tag);
        newComment.setCreatedOn(null);
        return newComment;
    }
}
