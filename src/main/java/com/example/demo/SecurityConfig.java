package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/login", "/api/auth/farmers/**", "/api/auth/farmers").permitAll() // Permit
                                                                                                                     // login
                                                                                                                     // and
                                                                                                                     // register
                        // endpoints

                        .anyRequest().authenticated() // Secure everything else
                )
                .httpBasic(Customizer.withDefaults()); // Use basic auth

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Set up in-memory user details for testing
        UserDetails user = User.withUsername("admin@example.com")
                .password("{noop}adminpassword") // NoOp password encoder for simplicity
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
