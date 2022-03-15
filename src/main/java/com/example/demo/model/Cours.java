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

    @ManyToOne(fetch = FetchType.EAGER)
    private User formateur;
    private UE uniteE;

    public Cours(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id != null ? id : 0;
    }
}
