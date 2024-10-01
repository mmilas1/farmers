package com.example.demo.service;

import com.example.demo.dtos.PetitionDto;
import com.example.demo.model.Farmer;
import com.example.demo.model.Inspector;
import com.example.demo.model.Petition;
import com.example.demo.repository.FarmerRepository;
import com.example.demo.repository.PetitionRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.example.demo.model.Petition.Status.APPROVED;
import static com.example.demo.model.Petition.Status.PENDING;
import static com.example.demo.model.Petition.Status.REJECTED;

@Service
public class PetitionService {

    private final PetitionRepository petitionRepository;
    private final FarmerRepository farmerRepository;

    public PetitionService(PetitionRepository petitionRepository, FarmerRepository farmerRepository) {
        this.petitionRepository = petitionRepository;
        this.farmerRepository = farmerRepository;
    }

    @Transactional(readOnly = true)
    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }

    // Approve a petition
    @Transactional
    public Petition approvePetition(Long id, Inspector inspector) {
        Optional<Petition> optionalPetition = petitionRepository.findById(id);
        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.setStatus(APPROVED.toString());
            petition.setInspector(inspector);
            return petitionRepository.save(petition);
        } else {
            throw new EntityNotFoundException("Petition not found.");
        }
    }

    // Reject a petition
    @Transactional
    public Petition rejectPetition(Long id) {
        Optional<Petition> optionalPetition = petitionRepository.findById(id);
        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.setStatus(REJECTED.toString());
            return petitionRepository.save(petition);
        } else {
            throw new EntityNotFoundException("Petition not found.");
        }
    }

    @Transactional
    public Petition createPetition(PetitionDto petitionDto) {
        if (petitionDto.getFarmerId() != null && farmerRepository.existsById(petitionDto.getFarmerId())) {
            Petition petition = new Petition();
            petition.setDescription(petitionDto.getDescription());
            petition.setLocation(petitionDto.getLocation());
            petition.setStatus(PENDING.toString());
            // Fetch farmer by ID, or else throw exception
            Farmer farmer = farmerRepository.findById(petitionDto.getFarmerId())
                .orElseThrow(() -> new EntityNotFoundException("Farmer was not found."));
            petition.setFarmer(farmer);
            return petitionRepository.save(petition);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create petition because the farmer does not exist.");
        }
    }

}
