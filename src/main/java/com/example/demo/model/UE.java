package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER)
    private List<Cours> cours;
}
