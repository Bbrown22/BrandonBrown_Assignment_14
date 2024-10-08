package com.coders.campus.assignment_14.web;

public class Message {
    private Long id; // Unique identifier for the message
    private String content; // The content of the message
    private String username; // The user who sent the message
    private Long channelId; // ID of the channel this message belongs to

    // No-argument constructor
    public Message() {
        // This constructor is intentionally left empty
    }

    // Constructor for id, content, username, and channelId
    public Message(Long id, String content, String username, Long channelId) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.channelId = channelId;
    }

    // Constructor for content, username, and channelId only (for creating new messages)
    public Message(String content, String username, Long channelId) {
        this.content = content;
        this.username = username;
        this.channelId = channelId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
