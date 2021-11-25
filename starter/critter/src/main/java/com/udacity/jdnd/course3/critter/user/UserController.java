package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.enities.Employee;
import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repositories.OwnerRepository;
import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.OwnerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    OwnerService ownerService;
    @Autowired
    OwnerService customerService;

    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;
// ,List<Long> petsToSave
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Owner returnedOwner = convertCustomerDTOToCustomerEntity(customerDTO);
        return convertCustomerToCustomerDTO(customerService.saveOwner(returnedOwner));
       //  throw new UnsupportedOperationException();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Owner> returnedOwners = customerService.getAllOwners();
        Function<? super Owner, ? extends CustomerDTO> function = this::convertCustomerToCustomerDTO;
        return returnedOwners.stream().map(function).collect(Collectors.toList());
       // throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToCustomerDTO(customerService.getOwnerByPetId(petId));     //throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee returnedEmployee = convertEmployeeDTOToEmployeeEntity(employeeDTO);
        return convertEmployeeToEmployeeDTO(employeeService.saveEmployee(returnedEmployee));
   //     throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {

        return convertEmployeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));
    //    throw new UnsupportedOperationException();
    }



    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.updateAvailabilityOfEmployee(daysAvailable,employeeId);

      //  throw new UnsupportedOperationException();

    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> listEmployees=employeeService.returnEmployeeByDateAndSkills(DayOfWeek.from(employeeDTO.getDate()),employeeDTO.getSkills());

        return listEmployees.stream().map(emp->convertEmployeeToEmployeeDTO(emp)).collect(Collectors.toList());

    }
    private Owner convertCustomerDTOToCustomerEntity(CustomerDTO customerDTO){
      //  Owner currentOwner = customerService.getOwnerById(customerDTO.getId());
        Owner currentOwner = new Owner();
        BeanUtils.copyProperties(customerDTO, currentOwner);
        if(customerDTO.getPetIds() != null && !customerDTO.getPetIds().isEmpty()) {
            List<Pet> pets=customerDTO.getPetIds().stream().map(petId->petService.findPetById(petId)).collect(Collectors.toList());
            currentOwner.setPets(pets);
        }
         return currentOwner;
    }
    private CustomerDTO convertCustomerToCustomerDTO(Owner owner){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(owner, customerDTO);
        if(owner.getPets()!=null && !owner.getPets().isEmpty())
              customerDTO.setPetIds(owner.getPets().stream().map(pet->pet.getId()).collect(Collectors.toList()));
        return customerDTO;
    }
    private Employee convertEmployeeDTOToEmployeeEntity(EmployeeDTO employeeDTO){
        Employee currentEmployee = new Employee();
        BeanUtils.copyProperties(employeeDTO, currentEmployee);
        return currentEmployee;
    }
    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}
