package aiss.dailyMotionMiner.mapper;

import aiss.dailyMotionMiner.model.dailymotion.User;
import aiss.dailyMotionMiner.model.videominer.VMUser;

public class UserMapper {
    public static VMUser toVMUser(User ptUser){
        VMUser user= new VMUser();
        user.setName(ptUser.getScreenname());
        user.setUser_link(ptUser.getUrl());
        if(ptUser.getAvatar25Url() != null && !ptUser.getAvatar25Url().isEmpty()){
            user.setPicture_link(ptUser.getAvatar25Url());
        }
        return user;
    }
}
