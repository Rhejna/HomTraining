package com.example.demo.serviceImpl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
