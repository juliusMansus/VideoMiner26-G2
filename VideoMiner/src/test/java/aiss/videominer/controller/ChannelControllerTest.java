package aiss.videominer.controller;

import aiss.videominer.Exception.ChannelNotFoundException;
import aiss.videominer.model.Channel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class ChannelControllerTest {

    @Autowired
    private ChannelController channelController;

    @Test
    @DisplayName("Test finding all channels")
    void findAll() {
        // Arrange: Ensure there's at least one channel in the database
        Channel channel = new Channel();
        channel.setId("test-all-id");
        channel.setName("Test Channel All");
        channelController.create(channel);

        // Act: Retrieve all channels
        List<Channel> channels = channelController.findAll();

        // Assert
        assertNotNull(channels, "The channel list should not be null");
        assertFalse(channels.isEmpty(), "The channel list should not be empty");
        assertTrue(channels.stream().anyMatch(c -> c.getId().equals("test-all-id")),
                "The list should contain the channel we just created");
    }

    @Test
    @DisplayName("Test finding a specific channel by ID")
    void findOne() throws ChannelNotFoundException {
        // Arrange: Create and save a channel
        String targetId = "find-one-test";
        Channel channel = new Channel();
        channel.setId(targetId);
        channel.setName("Target Channel");
        channelController.create(channel);

        // Act: Find the channel
        Channel result = channelController.findOne(targetId);

        // Assert
        assertNotNull(result, "The found channel should not be null");
        assertEquals(targetId, result.getId(), "The ID should match the requested one");
        assertEquals("Target Channel", result.getName(), "The name should match the saved one");
    }

    @Test
    @DisplayName("Test findOne throws exception when channel does not exist")
    void findOne_NotFound() {
        // Act & Assert
        assertThrows(ChannelNotFoundException.class, () -> {
            channelController.findOne("non_existent_id_999");
        }, "Should throw ChannelNotFoundException for a missing ID");
    }

    @Test
    @DisplayName("Test creating a new channel")
    void create() {
        // Arrange
        Channel newChannel = new Channel();
        newChannel.setId("creation-test-id");
        newChannel.setName("Created Channel");
        newChannel.setDescription("New Description");
        newChannel.setCreatedTime("2024-05-06");

        // Act
        Channel savedChannel = channelController.create(newChannel);

        // Assert
        assertNotNull(savedChannel, "The saved channel should not be null");
        assertEquals("creation-test-id", savedChannel.getId());
        assertEquals("Created Channel", savedChannel.getName());
        assertEquals("New Description", savedChannel.getDescription());
    }

    @Test
    @DisplayName("Test updating an existing channel")
    void update() throws ChannelNotFoundException {
        // Arrange: Create a channel to be updated
        String id = "update-test-id";
        Channel original = new Channel();
        original.setId(id);
        original.setName("Original Name");
        channelController.create(original);

        // Act: Prepare update data
        Channel updateData = new Channel();
        updateData.setName("Updated Name");
        updateData.setDescription("Updated Description");
        updateData.setCreatedTime("2024-12-25");

        channelController.update(updateData, id);

        // Assert: Verify changes by fetching again
        Channel result = channelController.findOne(id);
        assertEquals("Updated Name", result.getName(), "The name should be updated");
        assertEquals("Updated Description", result.getDescription(), "The description should be updated");
        assertEquals("2024-12-25", result.getCreatedTime(), "The date should be updated");
    }

    @Test
    @DisplayName("Test update throws exception when channel does not exist")
    void update_NotFound() {
        // Arrange
        Channel updateData = new Channel();
        updateData.setName("No Channel");

        // Act & Assert
        assertThrows(ChannelNotFoundException.class, () -> {
            channelController.update(updateData, "missing-id-update");
        }, "Should throw ChannelNotFoundException when trying to update a non-existent channel");
    }
}