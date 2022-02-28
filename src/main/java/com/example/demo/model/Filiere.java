package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.EAGER)
    private UE uniteE;

    public Long getId() {
        return id != null ? id : 0;
    }
}
