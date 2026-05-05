package aiss.dailymotionminer.service;

import aiss.dailymotionminer.mapper.ChannelMapper;
import aiss.dailymotionminer.model.dailymotion.User;
import aiss.dailymotionminer.model.videominer.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    VideoService videoService;

    @Value("${dailymotion.baseurl}")
    private String baseUrl;

    public VMChannel getChannel(String id, int maxVideos, int maxPages) {
        String fields = "id,screenname,url,avatar_25_url,description,created_time";
        User channel = restTemplate.getForObject(baseUrl + "/user/" + id + "?fields=" + fields, User.class);
        return ChannelMapper.toVMChannel(channel,videoService.getVideos(id, maxVideos, maxPages));
    }

}
