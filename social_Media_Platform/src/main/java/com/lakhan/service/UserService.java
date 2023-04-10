package com.lakhan.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lakhan.exception.UserNotFoundException;
import com.lakhan.model.User;
import com.lakhan.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setBio(updatedUser.getBio());
            user.setUpdatedAt(Instant.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

