package com.example.demo.controller;

import com.example.demo.model.Farmer;
import com.example.demo.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerService.getAllFarmers();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        try {
            Farmer savedFarmer = farmerService.saveFarmer(farmer);
            return new ResponseEntity<>(savedFarmer, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception (optional, depending on your logging setup)
            // e.g., logger.error("Error creating farmer", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
