package com.coders.campus.assignment_14.controller;

import com.coders.campus.assignment_14.services.UserService;
import com.coders.campus.assignment_14.web.User;
import com.coders.campus.assignment_14.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelController channelController; // Add ChannelController reference

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // Check login logic...

        // Generate a unique ID for the user
        Long uniqueUserId = channelController.generateUserId(); // Use the method from ChannelController

        // Create the user object
        User loggedInUser = new User(username, password, uniqueUserId);

        // Store the user in the session
        session.setAttribute("user", loggedInUser);

        return "redirect:/channels"; // Redirect to the channels page after login
    }

    // Display form to create user
    @GetMapping("/create")
    public String showCreateUserForm() {
        return "create-user";
    }

    // Handle user creation
    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Long uniqueUserId = System.currentTimeMillis(); // Generate a unique Long ID using timestamp
        User newUser = new User(username, password, uniqueUserId);
        userService.createUser(newUser);
        session.setAttribute("user", newUser);
        return "redirect:/channels";
    }

}
