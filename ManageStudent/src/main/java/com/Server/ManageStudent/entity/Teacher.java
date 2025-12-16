package com.Server.ManageStudent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teacher")
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;


    private String relativePhone;

    @Column(nullable = false, unique = true)
    private String teacherCode;

    @Column(nullable = false)
    private Float salary;

    @OneToOne(fetch = FetchType.LAZY, optional = false) //Avoid load User table when don't need
    @JoinColumn(name = "userId",referencedColumnName = "id",nullable = false) //Define foreign key
    private User user;
}
