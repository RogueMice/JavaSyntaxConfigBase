package com.Server.ManageStudent.dto;

import com.Server.ManageStudent.repository.model.role.Role;

import java.util.UUID;

public class UserDto {

     public UUID id = null;

    public String userName;

    public String password;

    public String fullName;

    public String address;

    public Role role;
}
