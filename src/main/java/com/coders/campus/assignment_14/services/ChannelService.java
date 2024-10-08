package com.coders.campus.assignment_14.services;

import com.coders.campus.assignment_14.web.Channel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelService {
    private List<Channel> channels = new ArrayList<>(); // In-memory storage

    // Create a new channel
    public void createChannel(Channel channel) {
        // Assign a unique ID to the new channel (for example, using the current size of the list)
        channel.setId((long) (channels.size() + 1)); // Simple ID generation
        channels.add(channel);
    }

    // Get all channels
    public List<Channel> getAllChannels() {
        return channels;
    }

    // Get a channel by ID
    public Channel getChannelById(Long id) {
        return channels.stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
