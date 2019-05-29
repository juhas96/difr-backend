package com.jkbjhs.postgresdemo.controller;

import com.jkbjhs.postgresdemo.exception.ResourceNotFoundException;
import com.jkbjhs.postgresdemo.model.User;
import com.jkbjhs.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers () {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
//        userRepository.flush();
        return userRepository.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId,
                           @Valid @RequestBody User userRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setEmail(userRequest.getEmail());
                    user.setUid(userRequest.getUid());
                    user.setUsername(userRequest.getUsername());
                    return userRepository.save(user);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

}
