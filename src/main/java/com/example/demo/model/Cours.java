package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private User formateur;

    /*@OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cours_outlinesCours",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "outlinesCours_id")
    )
    private Set<OutlinesCours> courseOutline;*/

    private boolean isComplete;

    public Cours(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? id : 0;
    }
}
