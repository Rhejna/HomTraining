package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
    @Transient
    private String filiereId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "etudiant_filiere",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id")
    )
    private Set<Filiere> filiere;
    private boolean solvabilite;

    public Etudiant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? id : 0;
    }
}
