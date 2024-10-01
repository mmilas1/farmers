package com.example.demo;

import lombok.Getter;

@Getter
public enum Role {
    FARMER("Farmer"),
    INSPECTOR("Inspector");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}