package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.Video;
import aiss.dailymotionminer.model.videominer.VMCaption;
import aiss.dailymotionminer.model.videominer.VMComment;
import aiss.dailymotionminer.model.videominer.VMVideo;

import java.util.ArrayList;
import java.util.List;

public class VideoMapper {
    public static VMVideo toVMVideo(Video video, List<VMCaption> captions){
        VMVideo newVideo=new VMVideo();
        newVideo.setId(video.getId());
        newVideo.setName(video.getTitle());
        newVideo.setDescription(video.getDescription());
        newVideo.setReleaseTime(video.getCreatedTime().toString());
        if(video.getOwner() != null){
            newVideo.setAuthor(UserMapper.toVMUser(video.getOwner()));
        }
        newVideo.setCaptions(captions);
        List<VMComment> comments=new ArrayList<>();
        if(video.getTags() != null) {
            for(int i=0;i<video.getTags().size();i++){
                comments.add(CommentMapper.toVMComment(video.getTags().get(i), video));
            }
        }
        newVideo.setComments(comments);
        return newVideo;
    }
}
