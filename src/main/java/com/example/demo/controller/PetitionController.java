package com.example.demo.controller;

import com.example.demo.model.Petition;
import com.example.demo.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;

    // Get all petitions (for inspectors to review)
    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        List<Petition> petitions = petitionService.getAllPetitions();
        return ResponseEntity.ok(petitions);
    }

    // Approve a petition
    @PutMapping("/{id}/approve")
    public ResponseEntity<Petition> approvePetition(@PathVariable Long id) {
        Petition petition = petitionService.approvePetition(id);
        return ResponseEntity.ok(petition);
    }

    // Reject a petition
    @PutMapping("/{id}/reject")
    public ResponseEntity<Petition> rejectPetition(@PathVariable Long id) {
        Petition petition = petitionService.rejectPetition(id);
        return ResponseEntity.ok(petition);
    }
}
