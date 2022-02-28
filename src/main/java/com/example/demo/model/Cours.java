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
public class Cours implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(nullable = false)
    private String libellé;
    @Column(nullable = false)
    private String reference;
    @ManyToMany(fetch = FetchType.EAGER)
    private Etudiant etudiant;
    @ManyToOne(fetch = FetchType.EAGER)
    private Formateur formateur;
    private UE unitéE;

    public Long getId() {
        return id != null ? id : 0;
    }
}
