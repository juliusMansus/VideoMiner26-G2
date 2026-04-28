package aiss.dailyMotionMiner.mapper;

import aiss.dailyMotionMiner.model.dailymotion.Video;
import aiss.dailyMotionMiner.model.videominer.VMComment;

import java.util.UUID;

public class CommentMapper {
    public static VMComment toVMComment(String tag, Video video){
        VMComment newComment=new VMComment();
        newComment.setId(UUID.randomUUID().toString());
        newComment.setText(tag);
        newComment.setCreatedOn(video.getCreatedTime().toString());
        return newComment;
    }
}
