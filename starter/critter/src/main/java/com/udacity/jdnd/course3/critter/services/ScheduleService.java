package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.enities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repositories.OwnerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;
    @Autowired
    OwnerRepository ownerRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
    public List<Schedule> returnAllScheduls() {
     return scheduleRepository.findAll();
    }
    public List<Schedule> returnSchedulsByEmployee(Long employeeId) {
       return scheduleRepository.getAllByEmployeesContains(employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFound::new));
        }
    public List<Schedule> returnSchedulsByPet(Long petId) {
        return scheduleRepository.getAllByPetsContains(petRepository.findById(petId).orElseThrow(PetNotFound::new));
            }

    public List<Schedule> returnSchedulsByOwner(Long ownerId) {
        Owner cuurentOwner = ownerRepository.findById(ownerId).orElseThrow(OwnerNotFound::new);
        return scheduleRepository.getAllByPetsIn(cuurentOwner.getPets());
    }
    public Schedule scheduleService(Schedule schedule, List<Long> listsOfEmplIds, List<Long> listsOfPetsIds) {
        List<Employee> selectedEmployees = employeeRepository.findAllById(listsOfEmplIds);
        List<Pet> selectedPets = petRepository.findAllById(listsOfPetsIds);
        schedule.setEmployees(selectedEmployees);
        return schedule;
                }



}

