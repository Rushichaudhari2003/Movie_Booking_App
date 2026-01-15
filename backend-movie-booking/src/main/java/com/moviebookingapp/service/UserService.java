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
        /* user.setRole("USER"); */
        if (user.getUsername().equalsIgnoreCase("admin")) {
    user.setRole("ADMIN");
} else {
    user.setRole("USER");
}

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
 /*   public void resetPassword(String username, String newPassword) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
} */

public void resetPassword(String username, String newPassword) {

    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Store old hash explicitly
    String oldEncodedPassword = user.getPassword();

    // Compare new raw password with old encoded password
    if (passwordEncoder.matches(newPassword, oldEncodedPassword)) {
        throw new RuntimeException("New password cannot be the same as old password");
    }

    // Encode & update
    String encodedNewPassword = passwordEncoder.encode(newPassword);
    user.setPassword(encodedNewPassword);

    userRepository.save(user);
}


}
