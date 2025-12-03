package com.Server.ManageStudent.controller;

import com.Server.ManageStudent.common.ResponseConfig;
import com.Server.ManageStudent.dto.LoginDto;
import com.Server.ManageStudent.dto.UserDto;
import com.Server.ManageStudent.service.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<ResponseConfig<Object>> login(@RequestBody LoginDto dto) {
        ResponseConfig<Object> user = authService.login(dto);
        return ResponseEntity.ok(user);
    }
}
