package aiss.dailyMotionMiner.mapper;

import aiss.dailyMotionMiner.model.dailymotion.User;
import aiss.dailyMotionMiner.model.videominer.VMChannel;
import aiss.dailyMotionMiner.model.videominer.VMVideo;

import java.util.List;
import java.util.UUID;

public class ChannelMapper {
    public static VMChannel toVMChannel(User user, List<VMVideo> videos){
        VMChannel newChannel=new VMChannel();
        newChannel.setId(UUID.randomUUID().toString());
        newChannel.setName(user.getScreenname());
        newChannel.setDescription(user.getDescription());
        newChannel.setCreatedTime(user.getCreatedTime().toString());
        newChannel.setVideos(videos);
        return newChannel;
    }
}
