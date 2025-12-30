package com.mvc_unit_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
    private int id;
    private String name;

    public User() {} // Needed for JSON (deserialization)

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters (for JSON)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

