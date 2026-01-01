package com.mvc_unit_test.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mvc_unit_test.entity.User;
import com.mvc_unit_test.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) { // constructor-based DI
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserName(@PathVariable int id) {
        String name = service.getUserName(id);
        if ("Unknown User".equals(name)) {
            return ResponseEntity.notFound().build();      // 404
        }
        return ResponseEntity.ok(name);                    // 200
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = service.createUser(user);
        return ResponseEntity.status(201).body(created);   // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id, @RequestBody User user) {
        Optional<User> updated = service.updateUser(id, user);
        
        if(updated.isPresent()) {
        	return new ResponseEntity(updated.get(),HttpStatus.OK);
        }
        
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = service.deleteUser(id);
        return deleted ? ResponseEntity.noContent().build() // 204
                       : ResponseEntity.notFound().build(); // 404
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.findAllUsers();

        if (users.isEmpty()) {
            // Return HTTP 204 No Content
            return ResponseEntity.noContent().build();
        }

        // Return HTTP 200 OK with the list
        return ResponseEntity.ok(users);
    }
}