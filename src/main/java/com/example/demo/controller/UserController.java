package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constantes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH + "user")
@CrossOrigin("*")
public class UserController {
    public UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

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

    @PutMapping("/")
    public User update(@RequestBody User user) {
        return this.service.updateUser(user);
    }

    @DeleteMapping("/delete/{idUser}")
    public String delete(@PathVariable Long idUser) {
        return this.service.deleteUser(idUser);
    }
}
