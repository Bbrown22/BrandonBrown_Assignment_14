package com.coders.campus.assignment_14.controller;

import com.coders.campus.assignment_14.services.UserService;
import com.coders.campus.assignment_14.web.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show the welcome page for new users
    @GetMapping("/welcome")
    public String showWelcomePage() {
        return "welcome"; // Should match welcome.html
    }

    // Handle user creation when name and password are submitted
    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User newUser = new User(username, password, null); // ID auto-generated in UserService
        userService.createUser(newUser);
        session.setAttribute("user", newUser);
        return "channels"; // Redirects to channels page to choose a channel
    }

    // Handle user logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/users/welcome"; // Go back to welcome page
    }
}
