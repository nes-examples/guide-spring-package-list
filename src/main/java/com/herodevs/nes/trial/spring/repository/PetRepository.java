package com.herodevs.nes.trial.spring.repository;

import com.herodevs.nes.trial.spring.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByName(String name);
    List<Pet> findByType(String type);
}
