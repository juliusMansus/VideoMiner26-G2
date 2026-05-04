package aiss.peertubeminer.service;

import aiss.peertubeminer.mapper.ChannelMapper;
import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMChannel;
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

    @Value("${peertube.baseurl}")
    private String baseUrl;

    public VMChannel getChannel(String channelHandle, int maxVideos, int maxComments)
    {
        Channel channel = restTemplate.getForObject(baseUrl + "/video-channels/"+channelHandle, Channel.class);
        return ChannelMapper.toVMChannel(channel,videoService.getVideos(channelHandle,maxVideos,maxComments));
    }
}
