package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.repositories.OwnerRepository;
import com.udacity.jdnd.course3.critter.services.OwnerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles web requests related to Pets.
 */
@RestController
@CrossOrigin
@RequestMapping(value="/pet", produces = "application/json; charset=UTF-8")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        //petService.savePet()
         Pet returnedPet = convertPetDTOToToPet(petDTO);
         return convertPetToPetDTO(petService.savePet(returnedPet,petDTO.getOwnerId()));
     //  throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
      //  Pet foundedPet = petService.findPetById(petId);
       // PetDTO petDTO = convertPetToPetDTO(foundedPet);
        return convertPetToPetDTO(petService.findPetById(petId));
       // throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> returnedPets = petService.fibAllPets();
        Function<? super Pet, ? extends PetDTO> function = this::convertPetToPetDTO;
        return returnedPets.stream().map(function).collect(Collectors.toList());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> returnedPets = petService.findAllPetsByOwner(ownerId);
        Function<? super Pet, ? extends PetDTO> function = this::convertPetToPetDTO;
        return returnedPets.stream().map(function).collect(Collectors.toList());
       // throw new UnsupportedOperationException();
    }


    private Pet convertPetDTOToToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        pet.setOwner(ownerService.getOwnerById(petDTO.getOwnerId()));
        return pet;
    }
    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        if(pet.getOwner() != null)
           petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

}
