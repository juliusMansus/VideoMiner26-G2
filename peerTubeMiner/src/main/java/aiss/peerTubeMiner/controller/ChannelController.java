package aiss.peerTubeMiner.controller;

import aiss.peerTubeMiner.exception.ChannelNotFoundException;
import aiss.peerTubeMiner.model.videominer.VMChannel;
import aiss.peerTubeMiner.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/peertube")
public class ChannelController {

    @Autowired
    ChannelService channelService;
    @Autowired
    RestTemplate restTemplate;

    @Value("${videominer.url}")
    private String videoMinerUrl;

    // GET - for testing only, does not send to VideoMiner
    @GetMapping("/{id}")
    public VMChannel getChannel (
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) throws ChannelNotFoundException {

        try{
            return channelService.getChannel(id, maxVideos, maxComments);
        }
        catch (Exception e){
            throw new ChannelNotFoundException();
        }

    }

    // POST - fetches from PeerTube and sends to VideoMiner
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel sendToVideoMiner(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxComments) throws ChannelNotFoundException {
        try {
            VMChannel channel = channelService.getChannel(id, maxVideos, maxComments);
            restTemplate.postForObject(
                    videoMinerUrl + "/api/v1/channels",
                    channel,
                    VMChannel.class);
            return channel;
        }
        catch (Exception e){
            throw new ChannelNotFoundException();
        }

    }
}
