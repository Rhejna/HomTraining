package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/***Cette classe réfère au chargé des études***/

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDEs extends User {
}
