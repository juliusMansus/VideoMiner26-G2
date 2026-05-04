package aiss.peertubeminer.mapper;

import aiss.peertubeminer.model.peertube.Comment;
import aiss.peertubeminer.model.videominer.VMComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {

    @Test
    @DisplayName("Should correctly map a real PeerTube Comment to a VMComment")
    void toVMComment() {
        Comment realPtComment = new Comment();
        realPtComment.setId(98765);
        realPtComment.setText("This is an exceptionally helpful video tutorial!");
        realPtComment.setCreatedAt("2023-11-20T15:45:00.000Z");

        VMComment result = CommentMapper.toVMComment(realPtComment);

        assertNotNull(result, "The mapped VMComment should not be null");

        assertEquals("98765", result.getId(), "ID should be mapped and converted to a String");
        assertEquals("This is an exceptionally helpful video tutorial!", result.getText(), "Text should match exactly");
        assertEquals("2023-11-20T15:45:00.000Z", result.getCreatedOn(), "Created time should be mapped from getCreatedAt");
    }
}