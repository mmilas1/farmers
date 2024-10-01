package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String location;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    private String status;

    @ManyToOne
    @JoinColumn(name = "inspector_id")
    private Inspector inspector;

    // Default constructor
    public Petition() {
    }

    // Parameterized constructor
    public Petition(String description, String location, Farmer farmer, String status, Inspector inspector) {
        this.description = description;
        this.location = location;
        this.farmer = farmer;
        this.status = status;
        this.inspector = inspector;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    @Override
    public String toString() {
        return "Petition{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", farmer=" + farmer +
                ", status='" + status + '\'' +
                ", inspector=" + inspector +
                '}';
    }
}
