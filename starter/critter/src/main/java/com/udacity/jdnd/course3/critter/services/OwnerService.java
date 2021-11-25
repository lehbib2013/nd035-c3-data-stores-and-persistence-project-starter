package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.enities.Owner;
import com.udacity.jdnd.course3.critter.enities.Pet;
import com.udacity.jdnd.course3.critter.repositories.OwnerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    PetRepository petRepository;

    public List<Owner>  getAllOwners() {
        return ownerRepository.findAll();
    }
    public Owner getOwnerById(Long ownerId){
        return ownerRepository.findById(ownerId).get();
    }
    public Owner saveOwner(Owner owner) {
        List<Long> petsIds = new ArrayList<>();
        List<Pet> collectedPets = new ArrayList<>();
        if(owner.getPets()!=null && !owner.getPets().isEmpty()){
            petsIds = owner.getPets().stream().map(p->p.getId()).collect(Collectors.toList());
        }
        if(petsIds !=null && !petsIds.isEmpty()) {
            collectedPets = petsIds.stream().map(id -> petRepository.findById(id).orElseThrow(OwnerNotFound::new)).collect(Collectors.toList());
            owner.setPets(collectedPets);
        }
        return ownerRepository.save(owner);
    }
  public  Owner getOwnerByPetId(Long petId){
        return petRepository.findById(petId).orElseThrow(PetNotFound::new).getOwner();
  }

}
