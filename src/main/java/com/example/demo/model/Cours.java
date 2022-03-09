package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

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
    private String libelle;
    @Column(nullable = false)
    private String reference;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Etudiant> etudiant;
    @ManyToOne(fetch = FetchType.EAGER)
    private User formateur;
    private UE uniteE;

    public Long getId() {
        return id != null ? id : 0;
    }
}
