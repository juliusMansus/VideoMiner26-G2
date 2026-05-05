package aiss.dailymotionminer.service;

import aiss.dailymotionminer.model.videominer.VMChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DailyMotionService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ChannelService channelService;

    @Value("${videominer.url}")
    private String videoMinerUrl;

    public VMChannel getChannel(String id, int maxVideos, int maxPages) {
        return channelService.getChannel(id, maxVideos, maxPages);
    }

    public VMChannel getChannelAndSendToVideoMiner(String id, Integer maxVideos, Integer maxPages) {
        VMChannel channel = channelService.getChannel(id, maxVideos, maxPages);
        restTemplate.postForObject(
                videoMinerUrl + "/api/channels",
                channel,
                VMChannel.class);
        return channel;
    }
}
