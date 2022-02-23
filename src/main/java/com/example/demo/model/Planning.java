package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planning implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private LocalDate date;
    @Column(nullable = false)
    private String reference;
    private CDEs chargeEtudes;
    @ManyToOne(fetch = FetchType.EAGER)
    private Formateur formateur;
    private Cours cours;
}
