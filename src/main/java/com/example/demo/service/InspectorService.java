package com.example.demo.service;

import com.example.demo.Role;
import com.example.demo.model.Inspector;
import com.example.demo.repository.InspectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InspectorService {

    private final InspectorRepository inspectorRepository;

    public InspectorService(InspectorRepository inspectorRepository) {
        this.inspectorRepository = inspectorRepository;
    }

    /**
     * Registers a new inspector.
     * 
     * @param inspector The inspector to be registered.
     * @return The saved inspector entity.
     */
    @Transactional
    public Inspector registerInspector(Inspector inspector) {
        inspector.setRole(Role.INSPECTOR); //Setting the role during registration of inspector
        return inspectorRepository.save(inspector);
    }

    @Transactional(readOnly = true)
    public Optional<Inspector> findByEmail(String email) {
        return inspectorRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Inspector> findAllInspectors() {
        return inspectorRepository.findAll();
    }
}
