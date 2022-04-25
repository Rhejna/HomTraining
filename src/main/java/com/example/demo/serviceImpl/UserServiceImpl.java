package com.example.demo.serviceImpl;

import com.example.demo.model.NotificationEmail;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.repository.VerificationTokenRepo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor @Transactional @Slf4j //le dernier permet de log tout ce qui se trouve dans la classe
public class UserServiceImpl implements UserService { //UserDetailsService
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenRepo verificationTokenRepository;
    private final MailService mailService;

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null){
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else{
            log.info("User found: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }*/

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
        //user.setUserCode(UUID.randomUUID().toString());
        try {
            User utilisateur = userRepo.findByEmail(user.getEmail());
            if (utilisateur != null && utilisateur.getId() > 0) {
                return new User();
            }
            /**Encoder le mot de passe*/
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(false);


            /**mail d'authentification**/
            String token = generateVerificationToken(user);
            mailService.sendMail(new NotificationEmail("Please Activate your Account",
                    user.getEmail(), "Your account just got create on HomTraing, " +
                    "please click on the below url to activate your account : " +
                    "http://localhost:8080/api/homTraining/auth/accountVerification/" + token));


            return userRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
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
