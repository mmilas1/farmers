package com.example.demo.service;

import com.example.demo.model.Petition;
import com.example.demo.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetitionService {

    @Autowired
    private PetitionRepository petitionRepository;

    public List<Petition> getAllPetitions() {
        return petitionRepository.findAll();
    }

    public Petition savePetition(Petition petition) {
        return petitionRepository.save(petition);
    }

    // Additional methods for petition approval, etc.
}
