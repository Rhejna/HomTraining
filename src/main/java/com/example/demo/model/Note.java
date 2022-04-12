package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private float point; /**c'est la note donnée*/
    @ManyToOne(fetch = FetchType.EAGER)
    private User formateur;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cours cours;
    @ManyToOne(fetch = FetchType.EAGER)
    private Etudiant etudiant;
    @Column(nullable = false, updatable = false, unique = true)
    private String noteCode;

    public Long getId() {
        return id != null ? id : 0;
    }
}
