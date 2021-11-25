package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.enities.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule>  getAllByPetsContains(Pet pet);
    List<Schedule>  getAllByEmployeesContains(Employee employee);
    List<Schedule> getAllByPetsIn(List<Pet> listOfPets);

    List<Schedule> getAllByActivitiesIn(List<EmployeeSkill> listOfSkills);
    List<Schedule> getAllByActivitiesContains(EmployeeSkill employeeSkill);
}