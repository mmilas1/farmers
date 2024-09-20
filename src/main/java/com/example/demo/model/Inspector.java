package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Inspector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // To be used for authentication via Spring Security

    @OneToMany(mappedBy = "inspector")
    private List<Petition> petitions;

    // Default constructor
    public Inspector() {
    }

    // Parameterized constructor
    public Inspector(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

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
}
