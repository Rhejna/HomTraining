package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate date;
    private String matricule;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Cours> cours;

    public Long getId() {
        return id != null ? id : 0;
    }
}
