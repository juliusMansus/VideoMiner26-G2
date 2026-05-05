package aiss.videominer.controller;

import aiss.videominer.Exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/videominer/channels")
public class ChannelController {

    @Autowired
    ChannelRepo channelRepo;

    @GetMapping
    public List<Channel> findAll(){
        return channelRepo.findAll();
    }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channel = channelRepo.findById(id);
        if (channel.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        return channel.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@RequestBody Channel channel) {
        return channelRepo.save(channel);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Channel upChannel, @PathVariable String id) throws ChannelNotFoundException {
        Optional<Channel> channelData = channelRepo.findById(id);
        if (channelData.isEmpty()) {
            throw new ChannelNotFoundException();
        }
        Channel _channel = channelData.get();
        _channel.setName(upChannel.getName());
        _channel.setDescription(upChannel.getDescription());
        _channel.setCreatedTime(upChannel.getCreatedTime());
        channelRepo.save(_channel);
    }

}
