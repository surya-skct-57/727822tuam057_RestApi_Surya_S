package com.example.signup.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.signup.model.User;
import com.example.signup.service.Service1;

import lombok.NonNull;

@RestController
@RequestMapping("api/products")
public class Controller {
    @Autowired
    private Service1 productService;

    @PostMapping("/createProduct")
    public ResponseEntity<User> createProduct(@NonNull @RequestBody User user) {
        User createdProduct = productService.createnewProduct(user);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("readProduct/{product_id}")
    public ResponseEntity<?> getUserById(@PathVariable int product_id) {
        Optional<User> user = productService.getModelById(product_id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/readProducts")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = productService.getAllusers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("updateProduct/{product_id}")
    public ResponseEntity<User> updateProduct(@PathVariable int product_id, @RequestBody User updateRequest) {
        User updated = productService.updateProduct(product_id, updateRequest);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{product_id}")
    public ResponseEntity<Void> removeProduct(@NonNull @PathVariable Integer product_id) {
        productService.removeProduct(product_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



