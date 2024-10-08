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

    // Show all available channels
    @GetMapping
    public String getAllChannels(Model model) {
        model.addAttribute("channels", channelService.getAllChannels());
        return "channels"; // Thymeleaf template name
    }

    // Show messages for a specific channel
    @GetMapping("/{id}")
    public String getChannelMessages(@PathVariable Long id, Model model, HttpSession session) {
        Channel channel = channelService.getChannelById(id);
        List<Message> messages = messageService.getMessagesByChannel(channel);
        model.addAttribute("channel", channel);
        model.addAttribute("messages", messages);
        return "channel"; // Thymeleaf template name
    }

    // Send a message to a specific channel
    @PostMapping("/{id}/message")
    public String sendMessage(@PathVariable Long id, @RequestParam String content, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            messageService.createMessage(id, content, user.getUsername());
        } else {
            // Handle case where user is null (e.g., redirect to login)
            return "redirect:/login"; // Adjust according to your logic
        }
        return "redirect:/channels/" + id; // Redirect to the channel page
    }

    // API endpoint for polling messages
    @GetMapping("/{id}/messages")
    @ResponseBody
    public List<Message> pollMessages(@PathVariable Long id) {
        Channel channel = channelService.getChannelById(id);
        return messageService.getMessagesByChannel(channel);
    }

    // Display form to create a new channel
    @GetMapping("/create")
    public String showCreateChannelForm() {
        return "create-channel"; // Ensure you have this template
    }

    // Handle new channel creation
    @PostMapping("/create")
    public String createChannel(@RequestParam String name) {
        Channel newChannel = new Channel(name); // Ensure you have a constructor that takes a name
        channelService.createChannel(newChannel);
        return "redirect:/channels"; // Redirect to channels page after creation
    }
}
