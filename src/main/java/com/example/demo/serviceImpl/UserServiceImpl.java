package com.example.demo.serviceImpl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    /***I kind of don't know what to do***/
    @Override
    @Transactional
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
