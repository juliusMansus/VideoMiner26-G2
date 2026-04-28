package aiss.dailyMotionMiner.controller;

import aiss.dailyMotionMiner.model.videominer.VMChannel;
import aiss.dailyMotionMiner.service.DailyMotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dailyMotion")
public class DailyMotionMinerController {

    @Autowired
    DailyMotionService dailyMotionService;

    @PostMapping("/{id}")
    public VMChannel fetchAndSendChannel(
            @PathVariable String channelId,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {
    
        return dailyMotionService.getChannelAndSendToVideoMiner(channelId, maxVideos, maxPages);
}
    @GetMapping("/{id}")
    public VMChannel getChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {

        return dailyMotionService.getChannel(id, maxVideos, maxPages);
    }
}