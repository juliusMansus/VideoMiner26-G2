package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.exception.ChannelNotFoundException;
import aiss.dailymotionminer.model.videominer.VMChannel;
import aiss.dailymotionminer.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailymotion")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel fetchAndSendChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) throws ChannelNotFoundException {
    try {

        return channelService.getChannelAndSendToVideoMiner(id, maxVideos, maxPages);
    } catch (Exception e) {
        throw new ChannelNotFoundException();
    }

}
    @GetMapping("/{id}")
    public VMChannel getChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) throws ChannelNotFoundException {

        try {
            return channelService.getChannel(id, maxVideos, maxPages);
        }
        catch (Exception e) {
            throw new ChannelNotFoundException();
        }

    }
}