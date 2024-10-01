package com.example.demo.service;

import com.example.demo.model.Inspector;
import com.example.demo.repository.InspectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InspectorService {

    @Autowired
    private InspectorRepository inspectorRepository;

    /**
     * Registers a new inspector.
     * 
     * @param inspector The inspector to be registered.
     * @return The saved inspector entity.
     */
    public Inspector registerInspector(Inspector inspector) {
        // You can add additional validation or logic here, such as checking if the
        // email is already in use.
        return inspectorRepository.save(inspector);
    }

    public Optional<Inspector> findByEmail(String email) {
        return inspectorRepository.findByEmail(email);
    }

    public List<Inspector> findAllInspectors() {
        return inspectorRepository.findAll();
    }
}
