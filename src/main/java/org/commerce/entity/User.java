package org.commerce.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Entity
@Table(name = "tshirt_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user", nullable = false)
    private UUID id;
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String lastName;
    @Column(name = "username", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String username;
    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String email;
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;
    @Column(name = "phone", columnDefinition = "VARCHAR(12)", length = 12)
    private String phone;
    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String address;
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(50) default 'USER'")
    private String role;

    public UserDetails orElseThrow() {
        return null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}