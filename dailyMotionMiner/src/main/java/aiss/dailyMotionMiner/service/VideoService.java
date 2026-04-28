package aiss.dailyMotionMiner.service;


import aiss.dailyMotionMiner.mapper.VideoMapper;
import aiss.dailyMotionMiner.model.dailymotion.Video;
import aiss.dailyMotionMiner.model.dailymotion.VideoList;
import aiss.dailyMotionMiner.model.videominer.VMVideo;
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
    CaptionService captionService;

    @Value("${dailymotion.baseurl}")
    private String baseUrl;

    public List<VMVideo> getVideos(String id, int maxVideos, int maxPages){
        String fields = "id,title,description,created_time,owner.id,owner.screenname,owner.url,owner.avatar_25_url,tags";
        List<VMVideo> videos = new ArrayList<>();
        int currentPage = 1;
        boolean hasMore = true;

        while (currentPage <= maxPages && hasMore) {
            VideoList videoList = restTemplate.getForObject(
                    baseUrl + "/user/" + id + "/videos?fields=" + fields + "&limit=" + maxVideos + "&page=" + currentPage, VideoList.class);

            if (videoList != null && videoList.getData() != null) {
                for (Video video : videoList.getData()) {
                    videos.add(VideoMapper.toVMVideo(video,
                            captionService.getCaptions(video.getId())));
                }
                hasMore = videoList.getHasMore();
            }
            else {
                hasMore = false;
            }
            currentPage++;
        }
        return videos;
    }
}
