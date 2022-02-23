package com.example.demo.serviceImpl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepo userRepo;

    public AdminServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        try {
            User utilisateur = userRepo.findByNumero(user.getNumero());
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
    @Transactional
    public User updateUser(User user) {
        try {
            User utilisateur = userRepo.findByNumero(user.getNumero());
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
        User utilisateur = userRepo.findById(id).orElse(new User());
        if (utilisateur.getId() > 0) {
            userRepo.delete(utilisateur);
            return "Utilisateur Supprimé !";
        }
        return "Erreur";
    }
}

