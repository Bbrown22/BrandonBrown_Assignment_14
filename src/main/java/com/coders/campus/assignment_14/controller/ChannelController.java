package com.coders.campus.assignment_14.controller;

import com.coders.campus.assignment_14.web.Channel;
import com.coders.campus.assignment_14.web.Message;
import com.coders.campus.assignment_14.web.User;
import com.coders.campus.assignment_14.services.ChannelService;
import com.coders.campus.assignment_14.services.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    // Display all available channels
    @GetMapping
    public String getAllChannels(Model model) {
        model.addAttribute("channels", channelService.getAllChannels());
        return "channels"; // Ensure "channels.html" exists in your templates folder
    }

    // Display messages for a specific channel
    @GetMapping("/{channelId}")
    public String getChannelMessages(@PathVariable Long channelId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/users/welcome"; // Redirect to welcome page if user is not logged in
        }

        Channel channel = channelService.getChannelById(channelId);
        if (channel == null) {
            return "redirect:/channels"; // Redirect to a safe page if channel doesn't exist
        }

        List<Message> messages = messageService.getMessagesByChannel(channel);
        model.addAttribute("channel", channel);
        model.addAttribute("messages", messages);
        return "channel"; // Ensure "channel.html" exists in your templates folder
    }

    // Send a message to a specific channel
    @PostMapping("/{channelId}/message")
    public String sendMessage(@PathVariable Long channelId, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            messageService.createMessage(channelId, content, user.getUsername(), user.getId());
        } else {
            return "redirect:/users/welcome"; // Redirect to welcome page if user is null
        }
        return "redirect:/channels/" + channelId;
    }

    // API endpoint for polling messages for a specific channel
    @GetMapping("/{channelId}/messages")
    @ResponseBody
    public List<Message> pollMessages(@PathVariable Long channelId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("User not authenticated");
        }

        Channel channel = channelService.getChannelById(channelId);
        if (channel == null) {
            throw new RuntimeException("Channel not found");
        }

        return messageService.getMessagesByChannel(channel);
    }

    // Display form to create a new channel
    @GetMapping("/create")
    public String showCreateChannelForm() {
        return "create-channel"; // Ensure "create-channel.html" exists in your templates folder
    }

    // Handle new channel creation
    @PostMapping("/create")
    public String createChannel(@RequestParam String name) {
        Channel newChannel = new Channel(name);
        channelService.createChannel(newChannel);
        return "redirect:/channels"; // Redirect to the channels page to show the updated list
    }
}
