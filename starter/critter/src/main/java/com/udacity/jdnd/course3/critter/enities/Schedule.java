package com.udacity.jdnd.course3.critter.enities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.time.DayOfWeek;
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @ManyToMany
    @JoinTable(
            name = "schedul_employee",
            joinColumns = { @JoinColumn(name = "schedul_id")},
            inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees;
    @ManyToMany
    @JoinTable(
            name = "schedul_pets",
            joinColumns = { @JoinColumn(name = "schedul_id")},
            inverseJoinColumns = { @JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets;
    //private LocalDate date;
    private LocalDate dateOfSchedule;
    @ElementCollection
    private Set<EmployeeSkill> activities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDateOfSchedule() {
        return dateOfSchedule;
    }

    public void setDateOfSchedule(LocalDate dateOfSchedule) {
        this.dateOfSchedule = dateOfSchedule;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
