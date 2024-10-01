package com.example.demo.security;

public class Authorized {

    private Authorized() {}

    public static final String FARMER =
        "hasAuthority('FARMER')";

    public static final String INSPECTOR =
        "hasAuthority('INSPECTOR')";
}
