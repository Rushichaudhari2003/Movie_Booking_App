 package com.moviebookingapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {

        if (userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("Username already exists");

        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    public User login(String username, String rawPassword) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }
}
