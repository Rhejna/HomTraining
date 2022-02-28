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
    @ManyToOne(fetch = FetchType.EAGER)
    private User chargeEtudes;
    @ManyToOne(fetch = FetchType.EAGER)
    private User formateur;
    private Cours cours;

    public Long getId() {
        return id != null ? id : 0;
    }
}
