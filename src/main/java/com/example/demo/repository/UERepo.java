package com.example.demo.repository;

import com.example.demo.model.Cours;
import com.example.demo.model.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UERepo extends JpaRepository<UE, Long> {
    UE findByReference(String reference);
    UE findByCours(Cours cours);
}
