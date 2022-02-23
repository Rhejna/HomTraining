package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface AdminService {
    List<User> allUsers();

    User getUser(String email);

    User createUser(User user);

    String deleteUser(Long id);

    User updateUser(User user);
}
