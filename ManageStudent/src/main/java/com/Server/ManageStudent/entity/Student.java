package com.Server.ManageStudent.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentCode;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;


    private String relativePhone;

    @OneToOne(fetch = FetchType.LAZY, optional = false) //Avoid load User table when don't need
    @JoinColumn(name = "userId",referencedColumnName = "id",nullable = false) //Define foreign key
    private User user;
}
