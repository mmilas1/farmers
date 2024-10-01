package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String name;
    private String email;
    private String password; 

    @OneToMany(mappedBy = "farmer")
    private List<Petition> petitions;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Petition> getPetitions() {
        return petitions;
    }

    public void setPetitions(List<Petition> petitions) {
        this.petitions = petitions;
    }

    // Constructor(s)
    public Farmer() { // Default constructor
    }

    public Farmer(String name, String email, String password) { // Parameterized constructor
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
