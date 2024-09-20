package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    // Default constructor
    public Petition() {
    }

    // Parameterized constructor
    public Petition(String title, String description, Farmer farmer) {
        this.title = title;
        this.description = description;
        this.farmer = farmer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    @Override
    public String toString() {
        return "Petition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", farmer=" + farmer +
                '}';
    }
}
