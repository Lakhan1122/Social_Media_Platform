package com.lakhan.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lakhan.model.User;
import com.lakhan.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
    
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        existingUser.setName(user.getName());
        existingUser.setBio(user.getBio());
        return userRepository.save(existingUser);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
    
    @GetMapping("/analytics/users")
    public long getTotalUserCount() {
        return userRepository.count();
    }
    
    @GetMapping("/analytics/users/top-active")
    public List<User> getTopActiveUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "posts.size")).stream()
            .limit(5)
            .collect(Collectors.toList());
    }
}


