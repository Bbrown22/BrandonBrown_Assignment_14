package com.coders.campus.assignment_14.repositories;

import com.coders.campus.assignment_14.web.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    // Save a new user
    public void save(User user) {
        users.add(user);
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
