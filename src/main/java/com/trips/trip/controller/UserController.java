package com.trips.trip.controller;

import com.trips.trip.model.User;
import com.trips.trip.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestParam String username, @RequestParam String password){
        return userService.loginUser(username,password);
    }
}
