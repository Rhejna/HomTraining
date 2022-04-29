package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

    @GetMapping("/role")
    public List<Role> allRoles() {
        return service.allRoles();
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

    /*@PostMapping("/role/addToUser")
    public void addRoleToUser(@RequestBody String userEmail, @RequestBody String roleName) {
        this.service.addRoleToUser(userEmail, roleName);
    }*/

    @PutMapping("/")
    public User update(@RequestBody User user) {
        return this.service.updateUser(user);
    }

    @DeleteMapping("/delete/{idUser}")
    public String delete(@PathVariable Long idUser) {
        return this.service.deleteUser(idUser);
    }

    /*@GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                User user = service.getUser(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",
                                user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }*/
}

@Data
class RoleToUserForm{
    private String userEmail;
    private String roleName;
}
