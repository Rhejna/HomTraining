package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity //for hibernate
@Data //crée des getters et setters
@NoArgsConstructor // cree un constructeur par defaut
@AllArgsConstructor
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String firstName;
    private String lastName;
    private String name;
    private String email;
    private String password;
    private int numero;
    private String userCode;
    /*private boolean enabled;
    private boolean tokenExpired;
    private String[] role;
    private String[] authorities;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();

    public Long getId() {
        return id != null ? id : 0;
    }

    public String getName() {
        return this.firstName + "" + this.lastName;
    }
}
