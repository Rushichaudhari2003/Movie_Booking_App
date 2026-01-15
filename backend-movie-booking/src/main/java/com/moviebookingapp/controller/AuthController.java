package com.moviebookingapp.controller;

import com.moviebookingapp.dto.LoginRequest;
import com.moviebookingapp.model.User;
import com.moviebookingapp.security.JwtService;
import com.moviebookingapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

 /*    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getUsername(), request.getPassword());

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        return Map.of("token", token);
    } */
   @PostMapping("/login")
public Map<String, String> login(@RequestBody LoginRequest request) {

    User user = userService.login(request.getUsername(), request.getPassword());

    String token = jwtService.generateToken(user.getUsername(), user.getRole());

    return Map.of(
        "token", token,
        "username", user.getUsername(),
        "role", user.getRole()
    );
}

}
