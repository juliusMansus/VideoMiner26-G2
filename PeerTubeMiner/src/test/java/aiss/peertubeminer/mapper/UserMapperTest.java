package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.User;
import aiss.peertubeminer.model.peertube.Avatar; // Assumed standard model location
import aiss.peertubeminer.model.videominer.VMUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Test
    @DisplayName("Should map correctly when user has an avatar list with elements (True condition)")
    void toVMUser_WithAvatars() {
        Avatar realAvatar = new Avatar();
        realAvatar.setFileUrl("https://peertube.local/images/avatar.jpg");

        List<Avatar> avatarList = new ArrayList<>();
        avatarList.add(realAvatar);

        User realUser = new User();
        realUser.setDisplayName("TechEducator");
        realUser.setUrl("https://peertube.local/users/techeducator");
        realUser.setAvatars(avatarList);

        VMUser result = UserMapper.toVMUser(realUser);

        assertNotNull(result, "VMUser should not be null");
        assertEquals("TechEducator", result.getName(), "Name should be mapped from displayName");
        assertEquals("https://peertube.local/users/techeducator", result.getUser_link(), "User link should be mapped from URL");
        assertEquals("https://peertube.local/images/avatar.jpg", result.getPicture_link(), "Picture link should map to the first avatar's URL");
    }

    @Test
    @DisplayName("Should map correctly when avatar list is null (False condition 1)")
    void toVMUser_NullAvatars() {
        User realUser = new User();
        realUser.setDisplayName("NoAvatarUser");
        realUser.setUrl("https://peertube.local/users/noavatar");
        realUser.setAvatars(null); // Explicitly null

        VMUser result = UserMapper.toVMUser(realUser);

        assertNotNull(result, "VMUser should not be null");
        assertEquals("NoAvatarUser", result.getName(), "Name should map correctly");
        assertEquals("https://peertube.local/users/noavatar", result.getUser_link(), "User link should map correctly");
        assertNull(result.getPicture_link(), "Picture link should remain null");
    }

    @Test
    @DisplayName("Should map correctly when avatar list is empty (False condition 2)")
    void toVMUser_EmptyAvatars() {
        User realUser = new User();
        realUser.setDisplayName("EmptyAvatarUser");
        realUser.setUrl("https://peertube.local/users/emptyavatar");
        realUser.setAvatars(new ArrayList<>()); // Empty list

        VMUser result = UserMapper.toVMUser(realUser);

        assertNotNull(result, "VMUser should not be null");
        assertEquals("EmptyAvatarUser", result.getName(), "Name should map correctly");
        assertEquals("https://peertube.local/users/emptyavatar", result.getUser_link(), "User link should map correctly");
        assertNull(result.getPicture_link(), "Picture link should remain null");
    }
}