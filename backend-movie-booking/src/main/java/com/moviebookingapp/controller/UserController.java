package com.moviebookingapp.controller;

import com.moviebookingapp.model.User;
import com.moviebookingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

  /*   @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username,
                                      @RequestParam String password) {
        return ResponseEntity.ok(userService.login(username, password));
    } */

   /*  @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    } */
   @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {
    try {
        return ResponseEntity.ok(userService.register(user));
    } catch (RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
@PostMapping("/reset-password")
public ResponseEntity<?> resetPassword(@RequestParam String username,
                                       @RequestParam String newPassword) {
    try {
        userService.resetPassword(username, newPassword);
        return ResponseEntity.ok("Password reset successful");
    } catch (RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}


}
