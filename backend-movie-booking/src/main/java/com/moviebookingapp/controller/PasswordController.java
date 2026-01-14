package com.moviebookingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moviebookingapp.service.UserService;

@RestController
@RequestMapping("/api/v1.0/moviebooking")
@CrossOrigin(origins = "http://localhost:4200")
public class PasswordController {

    private final UserService userService;

    public PasswordController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String username,
                                                @RequestParam String newPassword) {
        userService.resetPassword(username, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }
}
