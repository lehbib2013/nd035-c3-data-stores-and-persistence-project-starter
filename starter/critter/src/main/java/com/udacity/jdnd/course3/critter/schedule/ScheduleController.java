package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.enities.Schedule;
import com.udacity.jdnd.course3.critter.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
       // throw new UnsupportedOperationException();
        return convertScheduleToScheduleDTO(scheduleService.saveSchedule(convertScheduleDTOToScheduleEntity(scheduleDTO)));
       // scheduleRepository.findAll().stream().map(schedule->ConvertSschedule)
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.returnAllScheduls().stream().map(sched->convertScheduleToScheduleDTO(sched)).collect(Collectors.toList());
      //  throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.returnSchedulsByPet(petId).stream().map(sched->convertScheduleToScheduleDTO(sched)).collect(Collectors.toList());
       // throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.returnSchedulsByEmployee(employeeId).stream().map(sched->convertScheduleToScheduleDTO(sched)).collect(Collectors.toList());
        //throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.returnSchedulsByOwner(customerId).stream().map(sched->convertScheduleToScheduleDTO(sched)).collect(Collectors.toList());
         //throw new UnsupportedOperationException();
    }

    private Schedule convertScheduleDTOToScheduleEntity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        List<Employee> listOfEmployees = scheduleDTO.getEmployeeIds().stream().map(empId->employeeService.getEmployeeById(Long.parseLong(empId.toString()))).collect(Collectors.toList());
        List<Pet> listOfPets = scheduleDTO.getPetIds().stream().map(petId->petService.findPetById(Long.parseLong(petId.toString()))).collect(Collectors.toList());
        schedule.setDateOfSchedule(scheduleDTO.getDate());
        //  schedule.setActivities(scheduleDTO.getActivities());
        schedule.setEmployees(listOfEmployees);
        schedule.setPets(listOfPets);
        //Employee currentEmployee = employeeRepository.findAllById(ScheduleDTO.getEmployeeIds())
        return schedule;
    }
    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> empIds= schedule.getEmployees().stream().map(emp->emp.getId()).collect(Collectors.toList());
        List<Long> petsIds=schedule.getPets().stream().map(pet->pet.getId()).collect(Collectors.toList());
        scheduleDTO.setEmployeeIds(empIds);
        scheduleDTO.setPetIds(petsIds);
        scheduleDTO.setDate(schedule.getDateOfSchedule());
        return scheduleDTO;
    }

}
