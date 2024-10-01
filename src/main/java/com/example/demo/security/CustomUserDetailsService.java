package com.example.demo.security;

import com.example.demo.model.Farmer;
import com.example.demo.model.Inspector;
import com.example.demo.repository.FarmerRepository;
import com.example.demo.repository.InspectorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final FarmerRepository farmerRepository;
    private final InspectorRepository inspectorRepository;

    public CustomUserDetailsService(FarmerRepository farmerRepository, InspectorRepository inspectorRepository) {
        this.farmerRepository = farmerRepository;
        this.inspectorRepository = inspectorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Farmer> farmer = farmerRepository.findByEmail(email);
        if (farmer.isPresent()) {
            return new CustomUserDetails(farmer.get());
        }

        Optional<Inspector> inspector = inspectorRepository.findByEmail(email);
        if (inspector.isPresent()) {
            return new CustomUserDetails(inspector.get());
        }

        throw new UsernameNotFoundException("User not found");
    }
}