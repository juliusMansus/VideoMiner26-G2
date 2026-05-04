package aiss.peertubeminer.service;

import aiss.peertubeminer.mapper.VideoMapper;
import aiss.peertubeminer.model.peertube.Video;
import aiss.peertubeminer.model.peertube.VideoList;
import aiss.peertubeminer.model.videominer.VMVideo;
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
        List<VMVideo> videos = new ArrayList<>();
        int currentStart = 0;
        int limitPerPage = Math.min(maxVideos, 100);        // limit for getting videos from Peertube is 100 per request

        while(videos.size() < maxVideos) {
            int videosRemaining = maxVideos - videos.size();
            int count = Math.min(videosRemaining, limitPerPage);    // use the minimal required number of videos

            VideoList videoList = restTemplate.getForObject(
                    baseUrl + "/video-channels/" + channelHandle + "/videos?count=" + count + "&start=" + currentStart, VideoList.class);


            if (videoList == null || videoList.getData() == null){ break; }

            for (Video video : videoList.getData()) {
                videos.add(VideoMapper.toVMVideo(video,
                        commentService.getComments(video.getUuid(), maxComments),
                        captionService.getCaptions(video.getUuid())));
            }

            currentStart += videoList.getData().size();

            // break if there are no more videos
            if (videoList.getData().size() < count) {
                break;
            }
        }
        return videos ;
    }
}
