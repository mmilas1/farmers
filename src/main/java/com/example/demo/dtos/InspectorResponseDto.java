package com.example.demo.dtos;

import com.example.demo.model.Farmer;
import com.example.demo.model.Inspector;
import lombok.Data;

@Data
public class InspectorResponseDto {
    private Long id;
    private String name;
    private String email;

    public InspectorResponseDto(Inspector inspector) {
        this.id = inspector.getId();
        this.name = inspector.getName();
        this.email = inspector.getEmail();
    }
}
