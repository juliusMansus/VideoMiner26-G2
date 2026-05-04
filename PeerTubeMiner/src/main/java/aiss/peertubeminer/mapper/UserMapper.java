package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.User;
import aiss.peertubeminer.model.videominer.VMUser;

public class UserMapper {
    public static VMUser toVMUser(User ptUser) {
        VMUser user = new VMUser();
        user.setName(ptUser.getDisplayName());
        user.setUser_link(ptUser.getUrl());
        if (ptUser.getAvatars() != null && !ptUser.getAvatars().isEmpty())
            user.setPicture_link(ptUser.getAvatars().get(0).getFileUrl());
        return user;
    }
}
