package aiss.peerTubeMiner.mapper;

import aiss.peerTubeMiner.model.peertube.Video;
import aiss.peerTubeMiner.model.videominer.VMCaption;
import aiss.peerTubeMiner.model.videominer.VMComment;
import aiss.peerTubeMiner.model.videominer.VMVideo;

import java.util.List;

public class VideoMapper {
    public static VMVideo toVMVideo(Video ptVideo, List<VMComment> comments, List<VMCaption> captions) {
        VMVideo video = new VMVideo();
        video.setId(ptVideo.getUuid());
        video.setName(ptVideo.getName());
        video.setDescription(ptVideo.getDescription());
        video.setReleaseTime(ptVideo.getPublishedAt());
        if (ptVideo.getAccount() != null)
            video.setAuthor(UserMapper.toVMUser(ptVideo.getAccount()));
        video.setComments(comments);
        video.setCaptions(captions);
        return video;
    }
}
