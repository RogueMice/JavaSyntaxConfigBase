package com.Server.ManageStudent.dto.teacher;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TeacherRequestDto {

    public Long id;

    public String address;

    public String phoneNumber;

    public  String relativePhone;

    @NotBlank(message = "teacher code is null")
    @Size(min = 1, max = 10)
    public String teacherCode;

    @NotNull(message = "salary cannot be null")
    @PositiveOrZero(message = "salary must be >= 0")
    public Float salary;

    @NotNull(message = "user id is null")
    public Long userId;
}
