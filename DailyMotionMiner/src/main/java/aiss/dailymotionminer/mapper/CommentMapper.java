package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMComment;

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
