package aiss.dailymotionminer.controller;

import aiss.dailymotionminer.exception.ChannelNotFoundException;
import aiss.dailymotionminer.model.videominer.VMChannel;
import aiss.dailymotionminer.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Channel", description = "The channel management API from the DailyMotion miner")
@RestController
@RequestMapping("/dailymotion")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Operation(
            summary = "Retrieve a channel with the ID",
            description = "Get a VMChannel object by specifying its ID field.",
            tags = {"Channel", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel of the ID",
                    content = {@Content(
                            schema = @Schema(implementation = VMChannel.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Channel not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public VMChannel getChannel(
            @Parameter(description = "ID of channel to be searched.") @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) throws ChannelNotFoundException {
        try {
            return channelService.getChannel(id, maxVideos, maxPages);
        }
        catch (Exception e) { throw new ChannelNotFoundException(); }
    }

    @Operation(
            summary = "Create a channel",
            description = "Given a VMChannel object, fetches from DailyMotion and sends to VideoMiner.",
            tags = {"Channel", "POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Posted Channel object",
                    content = {@Content(
                            schema = @Schema(implementation = VMChannel.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Incorrect request format",
                    content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public VMChannel fetchAndSendChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) throws ChannelNotFoundException {
        try {
            return channelService.getChannelAndSendToVideoMiner(id, maxVideos, maxPages);
        }
            catch (Exception e) { throw new ChannelNotFoundException(); }
    }


}