package com.mvc.repository;

import java.util.Optional;

import com.mvc.entity.User;

public interface UserRepository {
    Optional<User> findById(int id);
}
