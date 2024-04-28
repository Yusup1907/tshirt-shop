package org.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tshirt_user")
public class User {

    @Id
    @Column(name = "id_user", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
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

}