package com.coders.campus.assignment_14.repositories;

import com.coders.campus.assignment_14.web.Channel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ChannelRepository {
    private final List<Channel> channels = new ArrayList<>();
    private Long idCounter = 1L;

    // Find all channels
    public List<Channel> findAll() {
        return channels;
    }

    // Save a new channel
    public void save(Channel channel) {
        channel.setId(idCounter++);
        channels.add(channel);
    }

    // Find channel by ID
    public Channel findById(Long id) {
        return channels.stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
