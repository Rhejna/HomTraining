package com.example.demo.serviceImpl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional @Slf4j //le dernier permet de log tout ce qui se trouve dans la classe
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public Role saveRole(Role role){
        log.info("Save new role {} ", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userEmail, String roleName){
        log.info("Add new role {} to user {}", roleName, userEmail);
        User user = userRepo.findByEmail(userEmail);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }


    /***I kind of don't know what to do***/
    @Override
    public String updatePassword(String password, Long UserId){
        return null;
//        try {
//            User user = userRepo.findById(UserId).get();
//            if (user != null && user.getPassword() != password) {
//                return user.setPassword(password);
//            }
//            return commandeRepo.save(commande);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new Commande();
//        }
    }

    @Override
    public List<User> allUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching a specific user {}", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        log.info("Save new user {} ", user.getFirstName());
        user.setUserCode(UUID.randomUUID().toString());
        try {
            User utilisateur = userRepo.findByUserCode(user.getUserCode());
            if (utilisateur != null && utilisateur.getId() > 0) {
                return new User();
            }
            return userRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    @Override
    public User updateUser(User user) {
        log.info("Update User");
        try {
            User utilisateur = userRepo.findByUserCode(user.getUserCode());
            if (utilisateur != null && utilisateur.getId() != user.getId()) {
                return new User();
            }
            return userRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    @Override
    public String deleteUser(Long id) {
        log.info("Delete User");
        User utilisateur = userRepo.findById(id).orElse(new User());
        if (utilisateur.getId() > 0) {
            userRepo.delete(utilisateur);
            return "Utilisateur Supprimé !";
        }
        return "Erreur";
    }
}
