package aiss.videominer.controller;

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
@RequestMapping("api/channels")
public class ChannelController {

    @Autowired
    ChannelRepo channelRepo;

    @GetMapping
    public List<Channel> findAll(){
        return channelRepo.findAll();
    }

    @GetMapping("/{id}")
    public Channel findOne(@PathVariable long id){
        Optional<Channel> channel=channelRepo.findById(id);
        return channel.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Channel create(@Valid @RequestBody Channel channel) {
        return channelRepo.save(new Channel(channel.getName(), channel.getDescription(),
                channel.getCreatedTime(), new ArrayList<>()));

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Channel upChannel, @PathVariable long id){
        Optional<Channel> channelData=channelRepo.findById(id);
        Channel _channel=channelData.get();
        _channel.setName(upChannel.getName());
        _channel.setDescription(upChannel.getDescription());
        _channel.setCreatedTime(upChannel.getCreatedTime());
        channelRepo.save(_channel);
    }

}
