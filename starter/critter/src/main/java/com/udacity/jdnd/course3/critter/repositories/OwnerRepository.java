package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
