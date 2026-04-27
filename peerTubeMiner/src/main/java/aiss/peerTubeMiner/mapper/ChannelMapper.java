package aiss.peerTubeMiner.mapper;

import aiss.peerTubeMiner.model.peertube.Channel;
import aiss.peerTubeMiner.model.videominer.VMChannel;
import aiss.peerTubeMiner.model.videominer.VMVideo;

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
