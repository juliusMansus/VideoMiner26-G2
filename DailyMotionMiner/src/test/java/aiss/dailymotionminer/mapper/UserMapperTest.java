package aiss.dailymotionminer.mapper;

import aiss.dailymotionminer.model.dailymotion.User;
import aiss.dailymotionminer.model.videominer.VMUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Test
    @DisplayName("Should map DailyMotion User to VMUser when avatar URL is present")
    void toVMUserWithAvatarUrl() {
        User dmUser = new User();
        dmUser.setScreenname("DailyMotion Creator");
        dmUser.setUrl("https://dailymotion.com/creator");
        dmUser.setAvatar25Url("https://dailymotion.com/thumb.jpg");

        VMUser result = UserMapper.toVMUser(dmUser);

        assertNotNull(result);
        assertEquals(dmUser.getScreenname(), result.getName());
        assertEquals(dmUser.getUrl(), result.getUser_link());
        assertEquals(dmUser.getAvatar25Url(), result.getPicture_link());
    }

    @Test
    @DisplayName("Should map DailyMotion User to VMUser when avatar URL is null or empty")
    void toVMUserWithoutAvatarUrl() {
        // Case 1: Null avatar URL
        User dmUser = new User();
        dmUser.setAvatar25Url(null);

        VMUser resultNull = UserMapper.toVMUser(dmUser);
        assertNull(resultNull.getPicture_link());

        // Case 2: Empty avatar URL
        dmUser.setAvatar25Url("");
        VMUser resultEmpty = UserMapper.toVMUser(dmUser);
        assertNull(resultEmpty.getPicture_link());
    }
}