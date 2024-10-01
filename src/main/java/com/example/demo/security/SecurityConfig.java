package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/h2-console/**").permitAll() // Allow H2 Database console access
                .requestMatchers("/api/auth/login", "/api/auth/farmers/**").permitAll() // Permit login and registration
                .anyRequest().authenticated() // Secure everything else
            )
            .httpBasic(Customizer.withDefaults()) // Use basic auth
            .headers(headers -> headers
                .addHeaderWriter((request, response) ->
                    response.setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080")) // Allow H2 console in iframe
            );

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
