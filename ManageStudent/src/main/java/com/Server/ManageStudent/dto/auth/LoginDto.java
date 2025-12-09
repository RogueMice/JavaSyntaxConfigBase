package com.Server.ManageStudent.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank(message = "user name can not empty.")
    public String userName;

    @NotBlank(message = "password can not empty.")
    public String password;
}
