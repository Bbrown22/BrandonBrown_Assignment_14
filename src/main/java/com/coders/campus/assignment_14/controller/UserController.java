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


    // Display form to create user
    @GetMapping("/create")
    public String showCreateUserForm() {
        return "create-user";
    }

    // Handle user creation
    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = new User(username, password);
        userService.createUser(user); // Use UserService to save the user
        session.setAttribute("user", user);  // Store user in session
        return "redirect:/channels"; // Redirect to channel selection page
    }

}
