package com.Server.ManageStudent.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentDto {
    public Long id;

    public String address;

    public String phoneNumber;

    public  String relativePhone;

    @NotBlank
    @Size(min = 1, max = 10)
    public String studentCode;

    public Long userId;
}
