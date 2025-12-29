package com.mvc.entity;

public class User {
    private int id;
    private String name;

    // Constructor
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() { 
    	return id; 
    	}
    
    public String getName() { 
    	return name; 
    	}
}
