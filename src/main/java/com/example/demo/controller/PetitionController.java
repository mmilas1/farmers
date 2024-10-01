package com.example.demo.controller;

import com.example.demo.dtos.PetitionDto;
import com.example.demo.model.Inspector;
import com.example.demo.model.Petition;
import com.example.demo.repository.InspectorRepository;
import com.example.demo.security.Authorized;
import com.example.demo.service.PetitionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/petitions")
public class PetitionController {

    @Autowired
    private PetitionService petitionService;

    @Autowired
    private InspectorRepository inspectorRepository;

    // Get all petitions (for inspectors to review)
    @GetMapping
    public ResponseEntity<List<Petition>> getAllPetitions() {
        List<Petition> petitions = petitionService.getAllPetitions();
        return ResponseEntity.ok(petitions);
    }

    // Create a petition by farmer
    @PostMapping
    @PreAuthorize(Authorized.FARMER)
    public ResponseEntity<Petition> createPetition(@RequestBody PetitionDto petition) {
        Petition createdPetition = petitionService.createPetition(petition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPetition);
    }

    // Approve a petition by inspector
    @PreAuthorize(Authorized.INSPECTOR)
    @PutMapping("/{id}/approve")
    public ResponseEntity<Petition> approvePetition(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var inspector = inspectorRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new EntityNotFoundException("Inspector not correlated to authenticated user"));
        Petition petition = petitionService.approvePetition(id, inspector);
        return ResponseEntity.ok(petition);
    }

    // Reject a petition by inspector
    @PreAuthorize(Authorized.INSPECTOR)
    @PutMapping("/{id}/reject")
    public ResponseEntity<Petition> rejectPetition(@PathVariable Long id) {
        Petition petition = petitionService.rejectPetition(id);
        return ResponseEntity.ok(petition);
    }
}
