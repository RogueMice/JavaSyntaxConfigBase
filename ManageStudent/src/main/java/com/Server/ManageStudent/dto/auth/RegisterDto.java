package com.Server.ManageStudent.dto.auth;

import com.Server.ManageStudent.dto.student.StudentDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public class RegisterDto {

    @NotBlank(message = "username can not empty !")
    public String userName;

    @NotBlank(message = "password can not empty !")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
            message = "Password might ≥ 6 characters , includes uppercase letters, lowercase letters and special characters."
    )
    public String password;

    public String fullName;

    @NotBlank(message = "role type can not empty !")
    public String roleType;

    public StudentDto studentDto;
}
