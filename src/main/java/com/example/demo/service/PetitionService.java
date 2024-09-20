package com.example.demo.service;

import com.example.demo.model.Petition;
import com.example.demo.repository.PetitionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }

    // Approve a petition
    public Petition approvePetition(Long id) {
        Optional<Petition> optionalPetition = petitionRepository.findById(id);
        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.setStatus("approved");
            return petitionRepository.save(petition);
        } else {
            throw new RuntimeException("Petition not found.");
        }
    }

    // Reject a petition
    public Petition rejectPetition(Long id) {
        Optional<Petition> optionalPetition = petitionRepository.findById(id);
        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.setStatus("rejected");
            return petitionRepository.save(petition);
        } else {
            throw new RuntimeException("Petition not found.");
        }
    }

    public Petition savePetition(Petition petition) {
        return petitionRepository.save(petition);
    }

}
