package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.repositories.OwnerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    public OwnerRepository ownerRepository;

    public Pet savePet(Pet petToSave,Long ownerId) {
        petToSave.setOwner(ownerRepository.findById(ownerId).orElseThrow(OwnerNotFound::new));
        return petRepository.save(petToSave);
       // return petToSave;
    }
    public Pet findPetById(Long idPet) {
        return petRepository.findById(idPet).orElseThrow(PetNotFound::new);
    }

   /* public Pet[] findPetsByOzner(Long idCustomer) {
        petRepository.findById(idCustomer);
    }*/
    public List<Pet> fibAllPets(){
        return petRepository.findAll();
    }
    public List<Pet> findAllPetsByOwner(Long id){
            return petRepository.getAllByOwnerId(id);
    }

}
