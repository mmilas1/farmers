package com.example.demo.dtos;

import com.example.demo.model.Farmer;
import lombok.Data;

@Data
public class FarmerResponseDto {
    private Long id;
    private String name;
    private String email;

    public FarmerResponseDto(Farmer farmer) {
        this.id = farmer.getId();
        this.name = farmer.getName();
        this.email = farmer.getEmail();
    }
}
