package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constantes;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService service;

    @GetMapping("/")
    public List<User> alls() {
        return service.allUsers();
    }

    @GetMapping("/email/{value}")
    public User getByEmail(@PathVariable String value) {
        return this.service.getUser(value);
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        return this.service.createUser(user);
    }

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role) {
        return this.service.saveRole(role);
    }

    @PostMapping("/role/addToUser")
    public void addRoleToUser(@RequestBody RoleToUserForm form) {
        this.service.addRoleToUser(form.getUserEmail(), form.getRoleName());
    }

    @PutMapping("/")
    public User update(@RequestBody User user) {
        return this.service.updateUser(user);
    }

    @DeleteMapping("/delete/{idUser}")
    public String delete(@PathVariable Long idUser) {
        return this.service.deleteUser(idUser);
    }
}

@Data
class RoleToUserForm{
    private String userEmail;
    private String roleName;
}
