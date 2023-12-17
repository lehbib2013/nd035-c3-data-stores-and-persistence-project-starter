package com.udacity.jdnd.course3.critter.enities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Type(type="nstring")
    private String name;

    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public Owner() {
    }

    public Owner(String name, String phoneNumber, String notes, List<Pet> pets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
