package com.coders.campus.assignment_14.services;

import com.coders.campus.assignment_14.web.Channel;
import com.coders.campus.assignment_14.web.Message;
import com.coders.campus.assignment_14.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    // Get messages for a specific channel
    public List<Message> getMessagesByChannel(Channel channel) {
        return messageRepository.findByChannel(channel.getId());
    }

    // Create a new message
    public void createMessage(Long channelId, String content, String username) {
        Message message = new Message();
        message.setContent(content);
        message.setUsername(username);
        message.setChannelId(channelId);
        messageRepository.save(message);
    }
}
