package com.example.demo.service;

import com.example.demo.Role;
import com.example.demo.dtos.InspectorDto;
import com.example.demo.model.Farmer;
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
     * @param inspectorDto The inspector to be registered.
     * @return The saved inspector entity.
     */
    @Transactional
    public Inspector registerInspector(InspectorDto inspectorDto, String encodedPassword) {
        Inspector inspector = new Inspector();
        inspector.setName(inspectorDto.getName());
        inspector.setEmail(inspectorDto.getEmail());
        inspector.setPassword(encodedPassword); // Use the encoded password
        inspector.setRole(Role.INSPECTOR); // Setting the role during registration of farmer
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
