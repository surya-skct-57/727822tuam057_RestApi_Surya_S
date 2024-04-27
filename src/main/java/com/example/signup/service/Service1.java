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

    public User createnewProduct(@NonNull User user) {
        return repo.save(user);
    }

    public List<User> getAllusers() {
        return repo.findAll();
    }

    public Optional<User> getModelById(@NonNull Integer product_id) {
        return repo.findById(product_id);
    }

    public User updateProduct(int product_id, @RequestBody User user) {
        return repo.findById(product_id).map(existingUser -> {
            existingUser.setProduct_name(user.getProduct_name());
            existingUser.setProduct_quantity(user.getProduct_quantity());
            existingUser.setProduct_category(user.getProduct_category());
            existingUser.setProduct_price(user.getProduct_price());
            return repo.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("User not found with this ID:" + product_id));
    }

    public void removeProduct(@NonNull Integer product_id) {
        repo.deleteById(product_id);
    }
}

