package aiss.dailyMotionMiner.controller;

import aiss.dailyMotionMiner.exception.ChannelNotFoundException;
import aiss.dailyMotionMiner.model.videominer.VMChannel;
import aiss.dailyMotionMiner.service.DailyMotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dailymotion")
public class DailyMotionMinerController {

    @Autowired
    DailyMotionService dailyMotionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel fetchAndSendChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) throws ChannelNotFoundException {
    try {

        return dailyMotionService.getChannelAndSendToVideoMiner(id, maxVideos, maxPages);
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
            return dailyMotionService.getChannel(id, maxVideos, maxPages);
        }
        catch (Exception e) {
            throw new ChannelNotFoundException();
        }

    }
}