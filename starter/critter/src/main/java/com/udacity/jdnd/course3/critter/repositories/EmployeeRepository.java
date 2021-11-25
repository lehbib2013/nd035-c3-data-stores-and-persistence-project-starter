package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.enities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;

/* JPARepository is an extension of CrudRepositoryv extension of CrudRepository that provides some other JPA-specific methods,
 such as getOne, which returns an Entity reference, just like entityManager.getReference.*/

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> getAllByDaysAvailableContains(DayOfWeek dayToSchedule);
}
