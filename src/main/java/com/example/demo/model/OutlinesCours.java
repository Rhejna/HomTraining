package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutlinesCours implements Serializable {
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
   private Cours cours;
   private boolean status;

   public Long getId() {
      return id != null ? id : 0;
   }
}
