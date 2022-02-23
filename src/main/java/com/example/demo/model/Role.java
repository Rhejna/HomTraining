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
public class Role implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<Privilege> privileges;
}
