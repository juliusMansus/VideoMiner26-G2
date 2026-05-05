package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Channel;
import aiss.peertubeminer.model.videominer.VMChannel;
import aiss.peertubeminer.model.videominer.VMVideo;

import java.util.List;

public class ChannelMapper {

    public static VMChannel toVMChannel(Channel ptChannel, List<VMVideo> videos) {
        VMChannel channel = new VMChannel();
        channel.setId(String.valueOf(ptChannel.getId()));
        channel.setName(ptChannel.getDisplayName());
        channel.setDescription(ptChannel.getDescription());
        channel.setCreatedTime(ptChannel.getCreatedAt());
        channel.setVideos(videos);
        return channel;
    }

}
