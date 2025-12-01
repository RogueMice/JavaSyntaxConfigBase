package com.Server.ManageStudent.repository.model;

import com.Server.ManageStudent.repository.model.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name= "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
