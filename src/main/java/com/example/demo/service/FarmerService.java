package com.example.demo.service;

import com.example.demo.Role;
import com.example.demo.dtos.FarmerDto;
import com.example.demo.model.Farmer;
import com.example.demo.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Transactional(readOnly = true)
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    @Transactional
    public Farmer saveFarmer(FarmerDto farmerDto, String encodedPassword) {
        Farmer farmer = new Farmer();
        farmer.setName(farmerDto.getName());
        farmer.setEmail(farmerDto.getEmail());
        farmer.setPassword(encodedPassword); // Use the encoded password
        farmer.setRole(Role.FARMER); // Setting the role during registration of farmer
        return farmerRepository.save(farmer);
    }
}
