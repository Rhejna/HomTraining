package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nom;
    @Column(nullable = false)
    private String reference;
    private Date dateDebut;
    private Date dateFin;
    private int nombreTranches;
    private int montantPension;
    @Transient
    private String uniteEId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "filiere_ue",
            joinColumns = @JoinColumn(name = "filiere_id"),
            inverseJoinColumns = @JoinColumn(name = "ue_id")
    )
    private Set<UE> uniteE;

    public Filiere(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? id : 0;
    }
}
