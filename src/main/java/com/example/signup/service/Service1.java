package com.example.signup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.signup.model.User;
import com.example.signup.repository.Repository;

@Service
public class Service1 {
    @Autowired
    public Repository repo;

    public User createnewuser(@NonNull User user) {
        return repo.save(user);
    }

    public List<User> getAllusers() {
        return repo.findAll();
    }

    public Optional<User> getModelByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User updateUser(@NonNull String email, @RequestBody User user) {
        return repo.findByEmail(email).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setDob(user.getDob());
            existingUser.setPassword(user.getPassword());
            return repo.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with this email:" + email));
    }

    public void removeUser(@NonNull Integer userId) {
        repo.deleteById(userId);
    }
}
