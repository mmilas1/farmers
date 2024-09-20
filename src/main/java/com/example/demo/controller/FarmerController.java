package com.example.demo.controller;

import com.example.demo.model.Farmer;
import com.example.demo.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // to match frontend port
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
    public ResponseEntity<Object> createFarmer(@RequestBody Farmer farmer) {
        try {
            Farmer savedFarmer = farmerService.saveFarmer(farmer);
            return new ResponseEntity<>(savedFarmer, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging

            // Return a meaningful error message
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error creating farmer. Please check the data and try again.");
            errorResponse.put("error", e.getMessage()); // Optionally include the exception message

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
