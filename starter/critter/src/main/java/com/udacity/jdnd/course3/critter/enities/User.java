package com.udacity.jdnd.course3.critter.enities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/* Creates a table for the parent class and each subclass. The subclass tables only have fields unique to their class */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Type(type="nstring")
    private String name;

    public long getId() {
        return id;
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
}
