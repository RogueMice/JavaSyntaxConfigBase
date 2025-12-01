package com.Server.ManageStudent.controller;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.UserDto;
import com.Server.ManageStudent.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("addOrUpdate")
    public ResponseEntity<ResponseConfig<UUID>> createOrUpdate(@RequestBody UserDto dto) {
        ResponseConfig<UUID> response = userService.createOrUpdate(dto);
        return ResponseEntity.ok(response);
    }
}
