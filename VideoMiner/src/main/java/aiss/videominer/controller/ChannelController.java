package aiss.videominer.controller;

import aiss.videominer.Exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Channel", description = "The channel management API")
@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {

    @Autowired
    ChannelRepo channelRepo;

    @Operation(
            summary = "Retrieve a channels list",
            description = "Get a list with all channels there are.",
            tags = {"Channel", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of channels",
                    content = {@Content(
                            schema = @Schema(implementation = Channel.class),
                            mediaType = "application/json")})
    })
    @GetMapping
    public List<Channel> findAll(){
        return channelRepo.findAll();
    }

    @Operation(
            summary = "Retrieve a channel with the ID",
            description = "Get a Channel object by specifying its ID field.",
            tags = {"Channel", "GET"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Channel of the ID",
                    content = {@Content(
                            schema = @Schema(implementation = Channel.class),
                            mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Channel not found",
                    content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public Channel findOne(
            @Parameter(description = "ID of channel to be searched.")
            @PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepo.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get();
    }

    @Operation(
            summary = "Create a channel",
            description = "Given a new Channel object, upload it.",
            tags = {"Channel", "POST"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Posted Channel object",
                    content = {@Content(
                            schema = @Schema(implementation = Channel.class),
                            mediaType = "application/json")})
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@RequestBody Channel channel) {
        return channelRepo.save(channel);
    }

    @Operation(
            summary = "Update an existing channel",
            description = "Update a Channel object by specifying its ID field.",
            tags = {"Channel", "PUT"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", description = "Channel not found",
                    content = {@Content(schema = @Schema())})
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Channel upChannel,
                       @Parameter(description = "ID of channel to be updated.")
                       @PathVariable String id) throws ChannelNotFoundException {
        Channel channel =  findOne(id);

        channel.setName(upChannel.getName());
        channel.setDescription(upChannel.getDescription());
        channel.setCreatedTime(upChannel.getCreatedTime());
        channelRepo.save(channel);
    }

    @Operation(
            summary = "Delete a channel with the ID",
            description = "Delete a Channel object by specifying its ID field.",
            tags = {"Channel", "DELETE"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", description = "Channel not found",
                    content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter(description = "ID of channel to be updated.")
                           @PathVariable String id) throws ChannelNotFoundException {
        if(!channelRepo.existsById(id)) {
            throw new ChannelNotFoundException();
        }
        channelRepo.deleteById(id);
    }

}
