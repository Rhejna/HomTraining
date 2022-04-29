package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    String updatePassword(String password, Long UserId);

    List<User> allUsers();

    List<Role> allRoles();

    User getUser(String email);

    User createUser(User user);

    String deleteUser(Long id);

    User updateUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);
}
