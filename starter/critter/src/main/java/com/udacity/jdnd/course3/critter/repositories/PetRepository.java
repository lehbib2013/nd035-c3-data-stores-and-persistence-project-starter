package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> getAllByOwnerId(Long id);
}
