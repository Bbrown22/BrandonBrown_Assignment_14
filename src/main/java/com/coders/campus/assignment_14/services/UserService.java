package com.coders.campus.assignment_14.services;

import com.coders.campus.assignment_14.web.User;
import com.coders.campus.assignment_14.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a new user with a unique ID
    public void createUser(User user) {
        if (user.getId() == null) {
            user.setId(userRepository.generateNewId()); // Use the repository to set a unique ID
        }
        userRepository.save(user);
    }

    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
