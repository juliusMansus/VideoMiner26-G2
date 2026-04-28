package aiss.dailyMotionMiner.mapper;

import aiss.dailyMotionMiner.model.dailymotion.SubtitleList;
import aiss.dailyMotionMiner.model.dailymotion.Video;
import aiss.dailyMotionMiner.model.videominer.VMCaption;
import aiss.dailyMotionMiner.model.videominer.VMComment;
import aiss.dailyMotionMiner.model.videominer.VMVideo;

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
