package com.example.demo.dtos;

import lombok.Data;

@Data
public class PetitionDto {
    private String description;
    private String location;
    private Long farmerId;
}
