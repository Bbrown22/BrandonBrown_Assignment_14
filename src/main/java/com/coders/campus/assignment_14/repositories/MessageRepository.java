package com.coders.campus.assignment_14.repositories;

import com.coders.campus.assignment_14.web.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MessageRepository {

    private final List<Message> messages = new ArrayList<>();

    // Save a new message
    public void save(Message message) {
        messages.add(message);
    }

    // Get messages by channel
    public List<Message> findByChannel(Long channelId) {
        return messages.stream()
                .filter(message -> message.getChannelId().equals(channelId))
                .collect(Collectors.toList());
    }
}
