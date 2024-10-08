package com.coders.campus.assignment_14.web;

import java.util.ArrayList;
import java.util.List;

public class Channel {
    private Long id;
    private String name;
    private List<Message> messages = new ArrayList<>();

    // Constructor for id and name
    public Channel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor for name only
    public Channel(String name) {
        this.name = name;
        this.messages = new ArrayList<>(); // Initialize the messages list
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
