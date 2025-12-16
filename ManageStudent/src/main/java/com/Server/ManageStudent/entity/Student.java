package com.Server.ManageStudent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    private String relativePhone;

    @Column(nullable = false, unique = true)
    public String studentCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false) //Avoid load User table when don't need
    @JoinColumn(name = "userId",referencedColumnName = "id",nullable = false, unique = true) //Define foreign key
    private User user;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
