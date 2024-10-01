package com.example.demo.controller;

import com.example.demo.model.Inspector;
import com.example.demo.service.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inspectors")
public class InspectorController {

    @Autowired
    private InspectorService inspectorService;

    @PostMapping
    public ResponseEntity<Inspector> createInspector(@RequestBody Inspector inspector) {
        Inspector savedInspector = inspectorService.registerInspector(inspector);
        return ResponseEntity.ok(savedInspector);
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
