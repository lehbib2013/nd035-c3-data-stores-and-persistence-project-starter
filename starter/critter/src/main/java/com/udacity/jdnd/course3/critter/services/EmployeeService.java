package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repositoryEmployee;
    public List<Employee>  getAllEmployees() {
        return repositoryEmployee.findAll();
    }
    public Employee getEmployeeById(Long employeeId){
        return repositoryEmployee.findById(employeeId).get();
    }
    public Employee saveEmployee(Employee empl) {
        repositoryEmployee.save(empl);
        return empl;
    }
    public List<Employee> returnEmployeeByDateAndSkills(DayOfWeek dayToSearchBy, Set<EmployeeSkill> skillsToSearchBy){
        return repositoryEmployee.getAllByDaysAvailableContains(dayToSearchBy)
                .stream().filter(p->p.getSkills().containsAll(skillsToSearchBy)).collect(Collectors.toList());
    };
  public void updateAvailabilityOfEmployee(Set<DayOfWeek> daysOfAvailability,Long employeeId) {
      Employee currEmployee = repositoryEmployee.findById(employeeId).orElseThrow(EmployeeNotFound::new);
      currEmployee.setDaysAvailable(daysOfAvailability);
      repositoryEmployee.save(currEmployee);
  }

}
