package com.example.demo.controller;

import com.example.demo.dtos.FarmerResponseDto;
import com.example.demo.dtos.InspectorDto;
import com.example.demo.dtos.InspectorResponseDto;
import com.example.demo.model.Inspector;
import com.example.demo.service.InspectorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/inspectors")
public class InspectorController {

    private final InspectorService inspectorService;
    private final PasswordEncoder passwordEncoder;

    public InspectorController(InspectorService inspectorService, PasswordEncoder passwordEncoder) {
        this.inspectorService = inspectorService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<InspectorResponseDto> createInspector(@RequestBody InspectorDto inspectorDto) {
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(inspectorDto.getPassword());
        // Save the farmer and return FarmerResponseDto, to hide the encrypted password from the response
        Inspector savedInspector = inspectorService.registerInspector(inspectorDto, encodedPassword);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new InspectorResponseDto(savedInspector));
    }

    @GetMapping("/{email}")
    public ResponseEntity<Inspector> getInspectorByEmail(@PathVariable String email) {
        Optional<Inspector> inspector = inspectorService.findByEmail(email);
        return inspector.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Inspector>> getAllInspectors() {
        List<Inspector> inspectors = inspectorService.findAllInspectors();
        return ResponseEntity.ok(inspectors);
    }
}
