package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/***Cette classe réfère à une Unité d'Enseignement***/

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UE implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String libellé;
    @Column(nullable = false)
    private String reference;
    @Transient
    private String coursId;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ue_cours",
            joinColumns = @JoinColumn(name = "ue_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private Set<Cours> cours;

    public UE(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? id : 0;
    }
}
