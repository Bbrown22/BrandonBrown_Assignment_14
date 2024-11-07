package com.coders.campus.assignment_14.repositories;

import com.coders.campus.assignment_14.web.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1); // Start ID generation at 1

    // Save a new user with a unique ID
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(generateNewId()); // Assign unique ID if not already set
        }
        users.add(user);
    }

    // Find user by username
    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    // Generate a new unique ID
    public Long generateNewId() {
        return idGenerator.getAndIncrement(); // Ensures each ID is unique and increments automatically
    }
}
