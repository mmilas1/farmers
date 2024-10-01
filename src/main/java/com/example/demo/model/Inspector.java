package com.example.demo.model;

import com.example.demo.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inspector implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password; // To be used for authentication via Spring Security
    private Role role;

    @OneToMany(mappedBy = "inspector")
    private List<Petition> petitions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(Role.INSPECTOR::getRoleName);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
