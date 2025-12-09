package com.Server.ManageStudent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeacherDto {

    public Long id;

    public String address;

    public String phoneNumber;

    public  String relativePhone;

    @NotBlank
    @Size(min = 1, max = 10)
    public String teacherCode;

    @Size(min = 0, message = "Salary is not < 0")
    private Double salary;

    public Long userId;
}
