package aiss.peerTubeMiner.service;

import aiss.peerTubeMiner.mapper.VideoMapper;
import aiss.peerTubeMiner.model.peertube.Video;
import aiss.peerTubeMiner.model.peertube.VideoList;
import aiss.peerTubeMiner.model.videominer.VMVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CommentService commentService;
    @Autowired
    CaptionService captionService;

    @Value("${peertube.baseurl}")
    private String baseUrl;

    public List<VMVideo> getVideos(String channelHandle, int maxVideos, int maxComments){
        VideoList videoList = restTemplate.getForObject(
                baseUrl + "/video-channels/" + channelHandle + "/videos?count=" + maxVideos, VideoList.class);
        List<VMVideo> videos = new ArrayList<>();
        if(videoList != null && videoList.getData() != null) {
            for(Video video : videoList.getData()) {
                videos.add(VideoMapper.toVMVideo(video,
                        commentService.getComments(video.getUuid(),maxComments),
                        captionService.getCaptions(video.getUuid())));
            }
        }
        return videos ;
    }
}
