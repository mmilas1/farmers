package com.example.demo.controller;

import com.example.demo.dtos.FarmerDto;
import com.example.demo.dtos.FarmerResponseDto;
import com.example.demo.model.Farmer;
import com.example.demo.service.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // to match frontend port
@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;
    private final PasswordEncoder passwordEncoder;

    public FarmerController(FarmerService farmerService, PasswordEncoder passwordEncoder) {
        this.farmerService = farmerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerService.getAllFarmers();
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FarmerResponseDto> createFarmer(@RequestBody FarmerDto farmerDto) {
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(farmerDto.getPassword());
        // Save the farmer and return FarmerResponseDto, to hide the encrypted password from the response
        Farmer savedFarmer = farmerService.saveFarmer(farmerDto, encodedPassword);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new FarmerResponseDto(savedFarmer));
    }
}
